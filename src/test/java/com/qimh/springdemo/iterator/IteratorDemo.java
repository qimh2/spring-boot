package com.qimh.springdemo.iterator;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * iterator 遍历的时候，不允许其他线程 修改集合
 */
public class IteratorDemo {

    public static void main(String[] args) {
        ArrayList list = Lists.newArrayList();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.add("dd");
        list.add("dd");
        list.add("dd");
        list.add("dd");
        list.add("dd");
        list.add("dd");
        list.add("dd");
        list.add("dd");
        list.add("dd");
        list.add("dd");
        list.add("dd");
        list.add("dd");
        list.add("dd");
        list.add("dd");
        list.add("dd");
        list.add("dd");
        //遍历
        new Thread(()->{
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()){
                String next = iterator.next();
                System.out.println(next);
            }
        }).start();

        new Thread(()->{
           list.remove(3);
        }).start();



    }

}
