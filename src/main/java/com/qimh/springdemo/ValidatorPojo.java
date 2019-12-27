package com.qimh.springdemo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * @author qiminhui
 */
public class ValidatorPojo {

    //非空判断
    @NotNull
    private Long id;

    //指定转换的日期格式及非空校验
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    @NotNull
    private Date date ;

    @Min(value = 1 ,message = "最小1岁")
    @Max(value = 120 ,message = "最大120岁")
    private Integer age;



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ValidatorPojo{" +
                "id=" + id +
                ", date=" + date +
                ", age=" + age +
                '}';
    }
}
