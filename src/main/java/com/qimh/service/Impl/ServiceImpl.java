package com.qimh.service.Impl;

import com.qimh.aspect.MethodLog;
import com.qimh.service.IService;
import com.qimh.vo.Person;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements IService {
    @Override
    @MethodLog
    public Person getPerson(Person person) {
        Person p = new Person();
        p.setAge(20);
        p.setName("张三");
        return p;
    }
}
