package com.qimh.impl;

public class MyClass implements MyInterface {
    @Override
    public void myMethod(String s) {
//        System.out.println(s);
    }



    public static void main(String[] args) {
        MyInterface obj = (ss) -> System.out.println(ss); // Lambda表达式实现接口
        obj.myMethod("hello world"); // 调用接口方法
    }
}
