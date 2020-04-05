package com.qimh.springdemo;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据验证，对请求的参数：validatorPojo 进行验证
 * @author qiminhui
 */
@Controller
public class RequestDataValidateController {


    /**
     * 注解：@Valid 表示启动验证机制
     * @param validatorPojo
     * @param errors
     * @return
     */
    @PostMapping(value = "/testValidate")
    @ResponseBody
    public Map<String,Object> testValidate(@Valid @RequestBody ValidatorPojo validatorPojo ,Errors errors){


        ValidatorPojo vp = new ValidatorPojo();
        vp.setId(validatorPojo.getId());
        vp.setDate(validatorPojo.getDate());
        vp.setAge(validatorPojo.getAge());


        Map<String,Object> errMap = new HashMap<String,Object>();

        //获取错误列表
        List<ObjectError> oes = errors.getAllErrors();


        return createErrMap(oes,errMap);
    }



    private Map<String,Object> createErrMap(List<ObjectError> oes,Map<String,Object> errMap){
        for (ObjectError oe:oes){
            String key = null;
            String msg = null;

            //字段错误
            if(oe instanceof FieldError){
                FieldError fe = (FieldError) oe;
                //获取错误验证字段名
                key = fe.getField();
            }else{
                //非字段错误

                //获取验证对象名称
                key = oe.getObjectName();
            }

            //错误信息
            msg = oe.getDefaultMessage();
            errMap.put(key,msg);

        }

        return errMap;

    }

    /**
     * 调用控制器前先执行这个方法
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        //绑定验证器
        binder.setValidator(new UserValidator());

    }



    /**
     * 注解：@Valid 表示启动验证机制
     * @param user
     * @param errors
     * @return
     */
    @PostMapping(value = "/userValidator")
    @ResponseBody
    public Map<String,Object> userValidator(@Valid @RequestBody User user ,Errors errors){




        Map<String,Object> errMap = new HashMap<String,Object>();

        //获取错误列表
        List<ObjectError> oes = errors.getAllErrors();


        return createErrMap(oes,errMap);
    }


}

