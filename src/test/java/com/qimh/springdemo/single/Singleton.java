package com.qimh.springdemo.single;

public class Singleton {
    //Singleton单例
    private Singleton(){}
    static {
        System.out.println("Singleton 加载。。。。");
    }
    private static class SingletonHolder{
        static {
            System.out.println("SingletonHolder 加载。。。。");
        }
        private static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {
//        System.out.println("getInstance()"+ getInstance());
        System.out.println("-----");
    }
}
