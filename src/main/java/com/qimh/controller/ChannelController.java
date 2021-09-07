package com.qimh.controller;

import com.qimh.enumeration.Channel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChannelController {

    @RequestMapping("getChannel")
    public String hello(Integer code){

        for(Channel channel:Channel.values()){
            if (channel.getIndex().equals(code)){
                return channel.getName();
            }
        }

        return "null";
    }

}
