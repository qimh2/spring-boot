package com.qimh.springdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Son extends Father {


    private String eye;
    private int sex;

    public String getEye() {
        return eye;
    }

    public void setEye(String eye) {
        this.eye = eye;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }


    public static void main(String[] args){
        Son son = new Son();
        son.setEye("big eys");
        son.setName("max");
        System.out.println(JSONObject.toJSONString(son));

    }
}
