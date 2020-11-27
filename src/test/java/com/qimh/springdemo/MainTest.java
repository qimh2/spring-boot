package com.qimh.springdemo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.qimh.entitys.UserEntity;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * @author qiminhui
 */
public class MainTest {

    public static void main(String[] args){
        testLocalDate2();
//        testLocalDate();
//        testComputeIfAbsent();
//        Lock lock = new ReentrantLock();



//        testOptionalMap();

//        testMapLoop();

        Byte var = 9;
        int i = var;
//        AtomicReference ar = new AtomicReference();
//        AtomicInteger ai = new AtomicInteger();
//        AtomicInteger ai2 = new AtomicInteger();
//        AtomicInteger ai3 = new AtomicInteger();


//        for(int m = 0;m < 3 ;m++){
//            System.out.println("m:" +m);
////            ar.getAndSet(m);
//            ai.addAndGet(m);
//            ai2.getAndSet(m);
//        }
////        System.out.println("ar:"+ar.get());
//        System.out.println("ai:"+ai.get());
//        System.out.println("ai2:"+ai2.get());



    }

    /**
     * 测试迭代器遍历的时候，不能修改集合的内容
     */
    public static void testIteator(){
        List<String> list = new ArrayList<>();
        for (int i = 0;i < 10;i++){
            list.add("a" + i);
        }



        Iterator<String> it2 = list. iterator();
        //验证iterator 迭代器遍历时，不能删除集合的元素，否则会报： java.util.ConcurrentModificationException
        new Thread() {
            @Override
            public void run() {
                while(it2. hasNext()){
                    String obj = it2. next();
                    System.out.println("remove");
                    it2.remove();


                }
            }
        }.start();


        Iterator<String> it = list. iterator();
        while(it. hasNext()){
            String obj = it. next();
            it.remove();
            System. out. println(obj);

        }
    }

    /**
     * 测试Optional--map
     */

    public static void testOptionalMap(){
        UserEntity user = new UserEntity();
        user.setName("aa");
        Optional<UserEntity> optional = Optional.ofNullable(user);

        // 如果容器的对象存在，则对其执行调用mapping函数得到返回值。然后创建包含mapping返回值的Optional，否则返回空Optional。
        String ss = optional.map(user1 -> user1.getName()).orElse("Unknown");
        System.out.println( ss);
    }


    /**
     * 测试Optional--FlaMap
     */

    public static void testOptionalFlaMap(){
        UserEntity user = new UserEntity();
        user.setName("aa");
        Optional<UserEntity> optional = Optional.ofNullable(user);

        // 如果容器的对象存在，则对其执行调用mapping函数得到返回值。然后创建包含mapping返回值的Optional，否则返回空Optional。
//        optional.flatMap(UserEntity::getName).map(user->user.);
//        System.out.println( ss);
    }

    /**
     * map 的遍历方式
     */
    public static void testMapLoop(){
        Map<String,String> maps = Maps.newHashMap();
        maps.put("aa","AA");
        maps.put("bb","BB");

        for(String key:maps.keySet()){
            System.out.println("key:" + key + "  value:" + maps.get(key));
        }
    }


    /**
     * map computeIfAbsent 和 computeIfPresent 用法
     */
    public static void testComputeIfAbsent(){
        // 创建一个 HashMap
        HashMap<String, Integer> prices = new HashMap<>();

        // 往HashMap中添加映射项
        prices.put("Shoes", 200);
        prices.put("Bag", 300);
        prices.put("Pant", 150);
        System.out.println("HashMap: " + prices);

        // 计算Shit的值
        //若值不存在
        int shirtPrice = prices.computeIfAbsent("Shirt", key -> 280);
        System.out.println("Price of Shirt: " + shirtPrice);

        // 输出更新后的HashMap
        System.out.println("Updated HashMap: " + prices);

        Map<String,Object> map = Maps.newHashMap();
        map.put("uids","11,22,33");
        //若值存在
        map.computeIfPresent("uids", (k, v) -> v.toString().split(","));
        System.out.println("map:" + JSON.toJSONString(map));
    }


    public static void testLocalDate(){
        LocalDate date = LocalDate.of(2018, 10, 31);//2018-10-31
        int year = date.getYear();//2018
        Month month = date.getMonth();//OCTOBER
        int day = date.getDayOfMonth();//31
        DayOfWeek dow = date.getDayOfWeek();//WEDNESDAY
        int len = date.lengthOfMonth();//31
        boolean leap = date.isLeapYear();//false

        LocalDate today = LocalDate.now();
        /**
         * java.time.LocalDate.atStartOfDay()方法将此日期与午夜时间组合在一起，以便在此日期开始时创建LocalDateTime。
         * //原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/javatime/javatime_localdate_atstartofday.html
         */
        LocalDateTime startOfDay = today.atStartOfDay();
        System.out.println("startOfDay:" + startOfDay);

        Instant start = startOfDay.toInstant(ZoneOffset.ofHours(8));
        System.out.println("start:" + start);
    }

    public static void testLocalDate2(){
        Instant start = null;
        Instant end = null;

        LocalDate today = LocalDate.now();
        start = today.with(TemporalAdjusters.firstDayOfYear()).atStartOfDay().toInstant(ZoneOffset.ofHours(8));
        end = today.with(TemporalAdjusters.firstDayOfNextYear()).atStartOfDay()
                .toInstant(ZoneOffset.ofHours(8));

        System.out.println("start:" + start + "  end:" + end);
    }



}
