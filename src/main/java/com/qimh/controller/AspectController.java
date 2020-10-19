package com.qimh.controller;

import com.qimh.service.IService;
import com.qimh.vo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qimh
 */
@RestController
@RequestMapping("/aspect/")
public class AspectController {

    @Autowired
    private IService service;

    @RequestMapping("getPerson")
    public Person getPerson(@RequestBody Person person){
        return service.getPerson(person);
    }

}

