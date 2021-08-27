package com.qimh.enumeration;

public enum Channel {
//    APP("APP"),XIGUA("XIGUA");
//    private String name;
//    Channel(String name){
//        this.name = name;
//    }


    APP(1,"APP"),XIGUA(2,"XIGUA");

    private Integer index;
    private String name;

    Channel(Integer index,String name){
        this.index = index;
        this.name = name;
    }
}
