package com.liucan.loda.annotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link org.springframework.beans.factory.config.BeanPostProcessor} implementation
 * that autowired {@link Loda} object
 *
 * @author liucan
 * @see LodaAutowired
 */
public class LodaAutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor,
        MergedBeanDefinitionPostProcessor {
    protected final Log logger = LogFactory.getLog(getClass());

    private static final String requiredParameterName = "required";

    private final ConfigurableListableBeanFactory beanFactory;

    public LodaAutowiredAnnotationBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * LodaAutowired injection cache, key:beanName,value:injection cache
     */
    private final Map<String, InjectionMetadata> injectionMetadataCache = new ConcurrentHashMap<>(256);

    /**
     * Find {@link LodaAutowired} injection metadata and cached
     */
    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        InjectionMetadata metadata = findLodaAutowiringMetadata(beanName, beanType, null);
        metadata.checkConfigMembers(beanDefinition);
    }

    private InjectionMetadata findLodaAutowiringMetadata(String beanName, Class<?> clazz, @Nullable PropertyValues pvs) {
        // Fall back to class name as cache key, for backwards compatibility with custom callers.
        String cacheKey = (StringUtils.hasLength(beanName) ? beanName : clazz.getName());
        InjectionMetadata metadata = this.injectionMetadataCache.get(cacheKey);
        if (InjectionMetadata.needsRefresh(metadata, clazz)) {
            synchronized (this.injectionMetadataCache) {
                metadata = this.injectionMetadataCache.get(cacheKey);
                if (InjectionMetadata.needsRefresh(metadata, clazz)) {
                    if (metadata != null) {
                        metadata.clear(pvs);
                    }
                    metadata = buildLodaAutowiringMetadata(clazz);
                    this.injectionMetadataCache.put(cacheKey, metadata);
                }
            }
        }
        return metadata;
    }

    private InjectionMetadata buildLodaAutowiringMetadata(Class<?> beanType) {
        List<InjectionMetadata.InjectedElement> injectedElements = new ArrayList<>();
        ReflectionUtils.doWithFields(beanType, field -> {
            AnnotationAttributes attributes = AnnotatedElementUtils.getMergedAnnotationAttributes(field, LodaAutowired.class);
            if (attributes != null) {
                if (Modifier.isStatic(field.getModifiers())) {
                    if (logger.isWarnEnabled()) {
                        logger.warn("LodaAutowired is not supported on static field!" + field);
                        return;
                    }
                }
                boolean required = !attributes.containsKey(requiredParameterName) || attributes.getBoolean(requiredParameterName);
                injectedElements.add(new LodaAutowiredFieldElement(field, required));
            }
        });

        return new InjectionMetadata(beanType, injectedElements);
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        InjectionMetadata metadata = findLodaAutowiringMetadata(beanName, bean.getClass(), pvs);
        try {
            metadata.inject(bean, beanName, pvs);
        } catch (BeanCreationException ex) {
            throw ex;
        } catch (Throwable ex) {
            throw new BeanCreationException(beanName, "Injection of LodaAutowired dependencies failed", ex);
        }
        return pvs;
    }

    /**
     * Class representing injection information about an {@link LodaAutowired} field.
     */
    private class LodaAutowiredFieldElement extends InjectionMetadata.InjectedElement {

        private final boolean required;

        private volatile boolean cached = false;

        @Nullable
        private volatile Object cachedFieldValue;

        public LodaAutowiredFieldElement(Field field, boolean required) {
            super(field, null);
            this.required = required;
        }

        @Override
        protected void inject(Object bean, @Nullable String beanName, @Nullable PropertyValues pvs) throws Throwable {
            Field field = (Field) this.member;
            Object value;
            if (this.cached) {
                value = this.cachedFieldValue;
            } else {
                value = beanFactory.getBean(field.getName(), field.getType());
                this.cachedFieldValue = value;
                this.cached = true;
            }
            if (value != null) {
                ReflectionUtils.makeAccessible(field);
                field.set(bean, value);
            } else {
                if (this.required) {
                    throw new BeanCreationException("Error create bean :" + beanName);
                }
            }
        }
    }
}
