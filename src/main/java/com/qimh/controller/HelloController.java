package com.qimh.controller;

import com.qimh.aspect.MethodLog;
import com.qimh.vo.Person;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * @author qimh
 */
public class HelloController {

    @RequestMapping("hello")
    @MethodLog(key = "cde",argsWhith = true)
    public String hello(@RequestBody Person person){
        return "hello world";
    }
}
