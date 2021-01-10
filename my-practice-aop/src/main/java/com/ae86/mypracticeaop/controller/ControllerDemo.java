package com.ae86.mypracticeaop.controller;

import com.ae86.mypracticeaop.annotation.MyLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Keifer
 */
@Controller
public class ControllerDemo {
    @RequestMapping("/aopDemo")
    @ResponseBody
    @MyLog()
    public String sayHello(){
        System.out.printf("Hello world!");
        return "哈哈哈！";
    }
}
