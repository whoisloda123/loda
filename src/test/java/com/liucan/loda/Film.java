package com.liucan.loda;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liucan
 * @version 2021/8/7
 */
@Data
public class Film implements Serializable {

    private Integer filmId;

    private String title;
}
