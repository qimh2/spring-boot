package com.qimh.enumeration;

public enum Channel {

//    APP(1,"APP"),XIGUA(2,"XIGUA")
    ;

    private Integer index;
    private String name;

    Channel(Integer index,String name){
        this.index = index;
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
