package com.liucan.loda;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liucan
 * @version 2021/8/2
 */
public interface ActorMapper {

    Actor findUserById(Integer id) throws Exception;

    Actor findUserByFirstname(@Param("name") String name) throws Exception;

    @Select("select * from actor")
    @ResultMap("actorResultMap")
    List<Actor> selectList();

    List<FilmActor> findFilmActorByActorId(@Param("actor_id") Integer actorId);

    List<FilmActor1> findFilms(@Param("actor_id") Integer actorId);
}
