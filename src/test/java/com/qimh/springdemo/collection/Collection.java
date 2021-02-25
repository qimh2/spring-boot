package com.qimh.springdemo.collection;

import com.google.common.collect.Lists;
import java.util.ArrayList;

public class Collection {

    public static void main(String[] args) {
        ArrayList list1 = Lists.newArrayList();
        list1.add("aa");
        list1.add("bb");
        list1.add("cc");

        ArrayList list2 = Lists.newArrayList();
        list2.add("bb");
        list2.add("cc");
        list2.add("dd");
        list2.add("ff");

        // list1 与 list2 存在相同元素，list1集合只保留list2中存在的元素
        //me---list1 与 list2 的交际，且清空list1->把交集放入lis1中
        list1.retainAll(list2);

        System.out.println(list1);
        System.out.println(list2);
    }

}
