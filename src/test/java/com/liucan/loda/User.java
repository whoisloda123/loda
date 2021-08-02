package com.liucan.loda;

import lombok.Data;

/**
 * 用户信息
 * @author liucan
 */
@Data
public class User {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
}
