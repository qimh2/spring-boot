package com.qimh.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author qiminhui
 */
@Aspect
@Component
public class MethodLogAspect {
    //定义切点
    @Pointcut("@annotation(com.qimh.aspect.MethodLog)")
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
            MethodLog methodLog = method.getAnnotation(MethodLog.class);
            String params = "";
            System.out.println("前置通知");
            long startTime = System.currentTimeMillis();
            if (method != null && methodLog.argsWhith()){
                params = methodLog.key() + " " +Arrays.toString(pjd.getArgs());
            }
            System.out.println("params:" + params);
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

}
