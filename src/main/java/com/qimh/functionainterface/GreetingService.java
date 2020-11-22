package com.qimh.functionainterface;

/**
 * 函数式接口
 * https://www.runoob.com/java/java8-functional-interfaces.html
 * https://blog.csdn.net/icarusliu/article/details/79495534
 */
@FunctionalInterface
public interface GreetingService {
    void sayMessage(String message);
}
