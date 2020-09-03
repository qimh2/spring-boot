package com.qimh.springdemo;

import com.google.common.collect.Maps;
import com.qimh.entitys.UserEntity;

import java.util.*;

/**
 * @author qiminhui
 */
public class MainTest {

    public static void main(String[] args){
//        Lock lock = new ReentrantLock();



//        testOptionalMap();

        testMapLoop();



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




}
