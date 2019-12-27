package com.qimh.springdemo;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @author qiminhui
 * 添加国际化解析器和拦截器
 */
@Configuration
public class Config implements WebMvcConfigurer{
    //国际化拦截器
    private LocaleChangeInterceptor lci = null;


    /**
     *  国际化解析器 ，注意，这个 bean name 要为localeResolver
     */
    @Bean(name="localeResolver")
    public LocaleResolver initLocalResolver(){
       SessionLocaleResolver slr = new SessionLocaleResolver();
       slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
       return  slr;
    }

    /**
     * 创建国际化拦截器
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        if (lci != null){
            return lci;
        }
        lci = new LocaleChangeInterceptor();
        //设置参数名称
        lci.setParamName("language");

        return lci;
    }

    /**
     * 给处理器增加国际化拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //这里将通过国际化拦截器的preHandle 方法 对请求的国际化区域参数进行修改
        registry.addInterceptor(localeChangeInterceptor());
    }
}
