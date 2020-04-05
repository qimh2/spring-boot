package com.qimh.springdemo;

import com.qimh.aspect.FieldAnnotation;
import com.qimh.entitys.UserEntity;

import java.lang.reflect.Field;

public class FiledAnnotationTest {


    public static void main(String[] args){

        testFieldAnnotion();

    }


    public static void testFieldAnnotion(){
        Class<UserEntity> clazz = UserEntity.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields){
            FieldAnnotation fieldAnnotation = field.getAnnotation(FieldAnnotation.class);
            if (fieldAnnotation != null){
                System.out.println("注解字段名字：" + fieldAnnotation.filedName());
            }
        }
    }


}
