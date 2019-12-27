package com.qimh.springdemo;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author qiminhui
 */
public class UserValidator implements Validator {

    /**
     * 验证器只支持User 类验证
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if(o == null){
            //直接在参数处报错，这样就不能进入控制器方法
            errors.rejectValue("",null,"用户不能为空");
        }

        //强制转换
        User user = (User) o;

        //用户名为空字符串
        if(StringUtils.isEmpty(user.getName())){
            //增加错误，可以进入控制器
            errors.reject("name",null,"用户名不能为空");
        }


    }
}
