package com.qimh.springdemo;

public class FanXingClass<T> {
    private T obj;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public static void main(String[] args) {
//        FanXingClass<String> fanXing = new FanXingClass<>();
//        fanXing.setObj("6666");
//        String str = fanXing.getObj();
        FanXingClass<Son> fathor = new FanXingClass<>();
        fathor.setObj(new Son());
        Father son = fathor.getObj();
    }
}
