package com.qimh.springdemo;

/**
 * @author qiminhui
 */
public class Demo {


    class A{
        int i ;
        A(int i ){
            this.i = i * 2;
        }
    }


    class B extends  A{
        B(int i){
            super(i);
            System.out.println(i);
        }

    }

}
