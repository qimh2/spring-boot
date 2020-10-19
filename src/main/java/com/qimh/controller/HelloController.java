package com.qimh.controller;

import com.qimh.aspect.MethodLog;
import com.qimh.service.IService;
import com.qimh.vo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * @author qimh
 */
public class HelloController {

    @Autowired
    private IService service;

    @RequestMapping("hello")
    @MethodLog(key = "cde",argsWhith = true)
    public String hello(@RequestBody Person person){
        return "hello world";
    }


}
