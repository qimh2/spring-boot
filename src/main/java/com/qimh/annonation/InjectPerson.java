package com.qimh.annonation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *注入工具是通过反射来得到注解的信息的，于是保留域必须使用RunTime
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectPerson {
    String username();
    int age();
}
