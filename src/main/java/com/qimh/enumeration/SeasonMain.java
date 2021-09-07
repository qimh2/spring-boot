package com.qimh.enumeration;

import java.util.Arrays;

public class SeasonMain {
    public static void main(String[] args) {
        Integer i = 0;
        System.out.println(++i);

//        System.out.println("原始数据：");
//        for (Season season : Season.values()) {
//            System.out.println(season);
//        }
//        System.out.println("-----------------------------");
//        DynamicEnumUtils.addEnum(Season.class, "WINTER", new Class[] {
//            java.lang.String.class, SeasonPattern.class }, new Object[] {
//            "winter", SeasonPattern.SNOW });
//        System.out.println("添加后的数据：");
//        for (Season season : Season.values()) {
//            System.out.println(season);
//        }
//        System.out.println("-----------------------------");
//        Season season = Season.valueOf("WINTER");
//        System.out.println("新添加的枚举类型可以正常使用：");
//        System.out.println(season.name());
//        System.out.println(season.getKey());
//        System.out.println(season.getSeasonPattern());


        synchronized (Channel.class) {
            DynamicEnumUtils.addEnum(Channel.class, "Active", new Class<?>[]{Integer.class,String.class}, new Object[]{3,"Active"});
            DynamicEnumUtils.addEnum(Channel.class, "Inactive", new Class<?>[]{Integer.class,String.class}, new Object[]{4,"Inactive"});
            DynamicEnumUtils.addEnum(Channel.class, "OP1", new Class<?>[]{Integer.class,String.class}, new Object[]{5,"OP1"});
            DynamicEnumUtils.addEnum(Channel.class, "OP2", new Class<?>[]{Integer.class,String.class}, new Object[]{6,"OP2"});
            DynamicEnumUtils.addEnum(Channel.class, "OP3", new Class<?>[]{Integer.class,String.class}, new Object[]{7,"OP3"});
            DynamicEnumUtils.addEnum(Channel.class, "OP4", new Class<?>[]{Integer.class,String.class}, new Object[]{8,"OP4"});
        }
//        Channel Channel =Channel.valueOf("5");
//        System.out.println(Channel);
        // Run a few tests just to show it works OK.
        System.out.println(Arrays.deepToString(Channel.values()));
        System.out.println("============================打印所有枚举（包括固定的和动态的），可以将数据库中保存的CIC以枚举的形式加载到JVM");
        for(Channel codeInfo:Channel.values()){
            System.out.println(codeInfo.name());
        }
    }

}
