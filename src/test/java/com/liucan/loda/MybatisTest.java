package com.liucan.loda;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author liucan
 * @date 2021/8/2
 */
public class MybatisTest {

    private SqlSessionFactory sqlSessionFactory;


    @Before
    public void init() throws Exception {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        InputStream inputStream = Resources.getResourceAsStream("MybatisConfig.xml");
        sqlSessionFactory = builder.build(inputStream);
    }

    @Test
    public void testFindByUserName() {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("test.findUserByUsername", "loda");
        System.out.println(user);
    }
}
