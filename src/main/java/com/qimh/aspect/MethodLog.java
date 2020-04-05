package com.qimh.aspect;

import java.lang.annotation.*;


/**
 * @Target说明了Annotation所修饰的对象范围
 * @Retention定义了该Annotation被保留的时间长短：
 * @Documented用于描述其它类型的annotation应该被作为被标注的程序成员的公共API
 * 参考连接：https://www.cnblogs.com/peida/archive/2013/04/24/3036689.html
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodLog {
    String key() default "abc";
    boolean argsWhith() default false;

}
