package com.qimh.aspect;

import com.alibaba.fastjson.JSONObject;
import com.qimh.dto.response.RestResp;
import com.qimh.entitys.Point;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletResponseWrapper;
import java.lang.reflect.Method;

@Aspect
@Component
public class PointRuleAspect {

    /**
     * 【public】 方法访问修饰符为public
     * 【*】 方法返回类型为任意类型
     * 【com.qimh.controller..getPoint】为com.qimh.controller包下的getPoint 方法
     * 【(..)】 方法参数为0个或多个任意参数
     * 参数解释连接：
     * https://xdoctorx.blog.csdn.net/article/details/107429359
     * https://blog.csdn.net/stayoutthere/article/details/55670723
     */
    //定义切点
//    @Pointcut("@annotation(com.qimh.aspect.PointRule)")
    @Pointcut("execution(public * com.qimh.controller..getPoint(..)) || execution(public * com.qimh.controller..getPoint2(..))")
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
            PointRule pointRule = method.getAnnotation(PointRule.class);

            // 下面两个数组中，参数值和参数名的个数和位置是一一对应的。
            Object[] objs = pjd.getArgs();
            String[] argNames = ((MethodSignature) pjd.getSignature()).getParameterNames(); // 参数名
            JSONObject paramMap = new JSONObject();
            for (int i = 0; i < objs.length; i++) {
                if (!(objs[i] instanceof ExtendedServletRequestDataBinder) && !(objs[i] instanceof HttpServletResponseWrapper)) {
                    paramMap.put(argNames[i], objs[i]);
                }
            }

            if (paramMap.get("point") instanceof Point &&  paramMap.getObject("point",Point.class).getPoint() > 1000){
                RestResp restResp = new RestResp<>();
                restResp.setData(new Point());
                restResp.setMsg("超过积分限制");
                restResp.setStatus("200");
                result = restResp;
                throw new Exception("超过积分限制");
            }



            result = pjd.proceed();
            System.out.println("积分后置通知");
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("积分异常通知");
        }
        System.out.println("积分返回通知");
        System.out.println("result:" + result);
        return result;

    }
}
