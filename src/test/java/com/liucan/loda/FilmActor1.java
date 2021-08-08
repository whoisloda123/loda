package com.liucan.loda;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liucan
 * @version 2021/8/7
 */
@Data
public class FilmActor1 implements Serializable {

    private Integer actorId;

    private List<Film> films;
}
