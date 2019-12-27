package com.qimh.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @author qiminhui
 */
@Controller
public class InternationalController {


    /**
     * 注入国际化消息接口对象
     * ，此对象根据读取国际化配置生成的
     */
    @Autowired
    private MessageSource messageSource;
    /**
     * 后台获取国际化信息
     * @return
     */
    @GetMapping("international")
    @ResponseBody
    public String international(HttpServletRequest request){
        //后台获取国际化区域
        Locale locale = LocaleContextHolder.getLocale();
        //获取国际化消息
        String msg = messageSource.getMessage("msg",null,locale);
        System.out.println("msg:" + msg);
        return msg;
    }


}
