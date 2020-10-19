package com.qimh.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletResponseWrapper;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @author qiminhui
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);
    //定义切点
    @Pointcut("execution(* com.qimh.controller..*(..))")
    public void doAspect() {
    }

    @Around("doAspect()")
    public Object doAround(ProceedingJoinPoint pjd) {
        Object result = null;
        try {
            //拦截的类名
            Class clazz = pjd.getTarget().getClass();
            //拦截的方法
            Method method = ((MethodSignature) pjd.getSignature()).getMethod();
            //获取请求参数
            printReqeustParams(pjd);
            System.out.println("前置通知");
            long startTime = System.currentTimeMillis();
            result = pjd.proceed();
            System.out.println(clazz.getName() + "->"+method.getName()+" cost:" + (System.currentTimeMillis() - startTime));
            System.out.println("后置通知");
        } catch (Throwable e) {
            System.out.println("异常通知");
        }
        System.out.println("返回通知");
        System.out.println("result:" + result);
        return result;

    }


    private void printReqeustParams(JoinPoint joinPoint){
        //通过uuid关联请求参数和返回参数
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 打印请求内容
        try {
            // 下面两个数组中，参数值和参数名的个数和位置是一一对应的。
            Object[] objs = joinPoint.getArgs();
            String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames(); // 参数名
            Map<String, Object> paramMap = new HashMap<String, Object>();
            for (int i = 0; i < objs.length; i++) {
                if (!(objs[i] instanceof ExtendedServletRequestDataBinder) && !(objs[i] instanceof HttpServletResponseWrapper)) {
                    paramMap.put(argNames[i], objs[i]);
                }
            }
            if (paramMap.size() > 0) {
                LOGGER.info("UUID->[{}],method->{},params->{}", uuid, joinPoint.getSignature(), JSONObject.toJSONString(paramMap));
            }
        } catch (Exception e) {
            LOGGER.error("[{}]AOP methodBefore:", uuid, e);
        }
    }

}
