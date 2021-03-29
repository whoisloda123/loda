package com.liucan.loda.controller;

import com.liucan.loda.annotation.CurrentUser;
import com.liucan.loda.annotation.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liucan
 */
@RestController
@RequestMapping("loda")
public class Controller {

    @GetMapping("test")
    public String test(@CurrentUser User user) {
        return "test";
    }
}
