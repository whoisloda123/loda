package com.liucan.loda;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liucan
 * @version 2021/8/7
 */
@Data
public class FilmActor implements Serializable {

    private Actor actor;

    private Film film;
}
