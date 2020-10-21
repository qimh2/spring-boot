package com.qimh.controller;

import com.qimh.aspect.MethodLog;
import com.qimh.service.IService;
import com.qimh.vo.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * @author qimh
 */
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private IService service;

    @RequestMapping("hello")
    @MethodLog(key = "cde",argsWhith = true)
    public String hello(@RequestBody Person person){
        System.out.println("LOGGER:" + LOGGER);
        LOGGER.info("hello:{}","world");
        return "hello world";
    }


}
