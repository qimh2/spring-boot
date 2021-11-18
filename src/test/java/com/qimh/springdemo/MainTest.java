package com.qimh.springdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.qimh.entitys.UserEntity;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

/**
 * @author qiminhui
 */
public class MainTest {

    private static Logger LOGGER = LoggerFactory.getLogger(MainTest.class);



    public static void main(String[] args) {

        String str2 = "00-00-00";
        String sub = "00-00-00-00";
        System.out.println(sub.startsWith(str2));

        System.out.println("test meger into current");
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }

        String str = "1:2:888:abc";
        System.out.println(str.substring(0,str.lastIndexOf(":")));


        JSONObject jb = new JSONObject();
        testJsonObject(jb);
        System.out.println("jb2" + jb);

        System.out.println("最近一个版本");

        System.out.println("test meger into current");

        JSONArray topicLikeTops = new JSONArray();
        topicLikeTops.add(1);
        topicLikeTops.add(2);
        topicLikeTops.add(3);
        topicLikeTops.add(4);
        topicLikeTops.add(5);
        topicLikeTops.add(6);
        topicLikeTops.add(7);
//        topicLikeTops.add(8);
//        topicLikeTops.add(9);
//        topicLikeTops.add(10);
        JSONArray usableTopicLikeTops = new JSONArray();
        builderUsableTopicliketops(topicLikeTops,usableTopicLikeTops,5,10);
        System.out.println("usableTopicLikeTops:" + JSON.toJSONString(usableTopicLikeTops));

//        StringBuffer sb = new StringBuffer();
//        sb.append("11").append("222");
//
//        System.out.println(sb);
//
//        System.out.println(Point.class.getName());
//        System.out.println(Point2.class.getName());
//        System.out.println(UserEntity.class.getName());
//
//        int num2 = 0;
//        try {
//            num2 = 1/0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            LOGGER.error("num2:{}",num2,e);
//        }
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.HOUR, 24);
//        System.out.println("dateTime:" + calendar.getTime());
//
//        String str = "11111     ";
//        System.out.println(str + "|");
//        System.out.println(str.trim() + "|");
//
//        AtomicInteger num = new AtomicInteger(0);
//        testInt(num);
//        testInt2(num);
////        Integer num = 1;
////        Integer num2 = 1;
////        System.out.println(num.equals(num2));
//
//        testStr();
//        System.out.println(testReturn());
//        testExceptionExecutor();

//        testLocalDate2();
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

    private static void testJsonObject(JSONObject jb) {
        jb.put("aa","aa");
        jb = JSON.parseObject("{\"bb\":\"bb\"}");
        jb.put("cc","cc");
        System.out.println("jb:" + jb);
    }

    private static void builderUsableTopicliketops(JSONArray topicLikeTops, JSONArray usableTopicLikeTops, int min,
        int max) {
        if (CollectionUtils.isEmpty(topicLikeTops)){
            return;
        }
        int topicLikeTopsSize = topicLikeTops.size();
        //数组长度小于等于最大值
        if (topicLikeTopsSize <= max){
            max = topicLikeTopsSize;
        }
        if (min == 0){
            for (int i = min; i < max; i++) {
                usableTopicLikeTops.add(topicLikeTops.get(i));
            }
        }else {
            //数组长度小于等于最小值
            if (topicLikeTopsSize < min){
                return;
            }
            for (int i = (min -1); i < max; i++) {
                usableTopicLikeTops.add(topicLikeTops.get(i));
            }
        }
    }


    public static void testInt(AtomicInteger num) {
        System.out.println(num.get());
        num.getAndIncrement();
    }

    public static void testInt2(AtomicInteger num) {
        System.out.println(num);
        num.getAndIncrement();
        System.out.println(num);
    }

    /**
     * 测试迭代器遍历的时候，不能修改集合的内容
     */
    public static void testIteator() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("a" + i);
        }

        Iterator<String> it2 = list.iterator();
        //验证iterator 迭代器遍历时，不能删除集合的元素，否则会报： java.util.ConcurrentModificationException
        new Thread() {
            @Override
            public void run() {
                while (it2.hasNext()) {
                    String obj = it2.next();
                    System.out.println("remove");
                    it2.remove();


                }
            }
        }.start();

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String obj = it.next();
            it.remove();
            System.out.println(obj);

        }
    }

    /**
     * 测试Optional--map
     */

    public static void testOptionalMap() {
        UserEntity user = new UserEntity();
        user.setName("aa");
        Optional<UserEntity> optional = Optional.ofNullable(user);

        // 如果容器的对象存在，则对其执行调用mapping函数得到返回值。然后创建包含mapping返回值的Optional，否则返回空Optional。
        String ss = optional.map(user1 -> user1.getName()).orElse("Unknown");
        System.out.println(ss);
    }


    /**
     * 测试Optional--FlaMap
     */

    public static void testOptionalFlaMap() {
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
    public static void testMapLoop() {
        Map<String, String> maps = Maps.newHashMap();
        maps.put("aa", "AA");
        maps.put("bb", "BB");

        for (String key : maps.keySet()) {
            System.out.println("key:" + key + "  value:" + maps.get(key));
        }
    }


    /**
     * map computeIfAbsent 和 computeIfPresent 用法
     */
    public static void testComputeIfAbsent() {
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

        Map<String, Object> map = Maps.newHashMap();
        map.put("uids", "11,22,33");
        //若值存在
        map.computeIfPresent("uids", (k, v) -> v.toString().split(","));
        System.out.println("map:" + JSON.toJSONString(map));
    }


    public static void testLocalDate() {
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

    public static void testLocalDate2() {
        Instant start = null;
        Instant end = null;

        LocalDate today = LocalDate.now();
        start = today.with(TemporalAdjusters.firstDayOfYear()).atStartOfDay().toInstant(ZoneOffset.ofHours(8));
        end = today.with(TemporalAdjusters.firstDayOfNextYear()).atStartOfDay()
            .toInstant(ZoneOffset.ofHours(8));

        System.out.println("start:" + start + "  end:" + end);
    }


    public static void testExceptionExecutor() {
        try {
            int i = 1 / 0;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("end");
    }

    public static String testReturn() {
        if (true) {
            return testReturn2();
        }
        System.out.println("return1");
        return "";
    }

    public static String testReturn2() {
        System.out.println("return2");
        return "aa";
    }

    public static void testStr() {
        String orderInfo = "{\n" +
            "    \"partsTotalQuantity\": \"2\", \n" +
            "    \"model\": \"AS28车型\", \n" +
            "    \"tel\": \"17688880002\", \n" +
            "    \"ascAddress\": \"江苏省常熟市开元东路6号\", \n" +
            "    \"reservationServiceType\": \"维修\", \n" +
            "    \"reservationDate\": \"2020-11-29\", \n" +
            "    \"ascCode\": \"2100396\", \n" +
            "    \"type\": \"add\", \n" +
            "    \"discountAmount\": \"0.0\", \n" +
            "    \"userType\": \"普通用户\", \n" +
            "    \"ascMailbox\": \"621150030@qq.com\", \n" +
            "    \"actualTotalAmount\": \"147.00\", \n" +
            "    \"licenseNo\": \"沪SS12582\", \n" +
            "    \"vehId\": \"100861526381277\", \n" +
            "    \"bookingOrderId\": \"1173100001\", \n" +
            "    \"userId\": \"96921167798856\", \n" +
            "    \"name\": \"嘚嘚\", \n" +
            "    \"totalAmount\": \"147.00\", \n" +
            "    \"tbMaintenanceOrderSaicItemTobs\": [\n" +
            "        {\n" +
            "            \"partCode\": \"10514332\", \n" +
            "            \"itemPartName\": \"风窗玻璃刮水器刮片总成(驾驶侧)\n" +
            "\", \n" +
            "            \"partPrice\": \"58.5\", \n" +
            "            \"partCount\": \"1\", \n" +
            "            \"serviceItemName\": \"更换前雨刮片（一对）\"\n" +
            "        }, \n" +
            "        {\n" +
            "            \"partCode\": \"10514335\", \n" +
            "            \"itemPartName\": \"风窗玻璃刮水器刮片总成(乘客侧)\n" +
            "\", \n" +
            "            \"partPrice\": \"58.5\", \n" +
            "            \"partCount\": \"1\", \n" +
            "            \"serviceItemName\": \"更换前雨刮片（一对）\"\n" +
            "        }\n" +
            "    ], \n" +
            "    \"tbMaintenanceOrderSaicCostTobs\": [\n" +
            "        {\n" +
            "            \"typeName\": \"常规保养\", \n" +
            "            \"manHourPrice\": \"100.0\", \n" +
            "            \"vin\": \"LSJA24U90KS160688\", \n" +
            "            \"asPlatformCode\": \"荣威RX5MAX\", \n" +
            "            \"laborCode\": \"681A009\", \n" +
            "            \"service\": \"更换前雨刮片\", \n" +
            "            \"manHourCount\": \"0.3\", \n" +
            "            \"ascCode\": \"2100396\", \n" +
            "            \"sysName\": \"车身电器\", \n" +
            "            \"laborDesc\": \"更换前雨刮片\"\n" +
            "        }\n" +
            "    ], \n" +
            "    \"payMethod\": \"2\", \n" +
            "    \"isNeedCoupon\": 0, \n" +
            "    \"uniOrderId\": \"781570925664632832\", \n" +
            "    \"mileage\": \"0.0\", \n" +
            "    \"reservationPeriod\": \"10:15-10:45\", \n" +
            "    \"ascHotLine\": \"0512-52670583\", \n" +
            "    \"orderType\": \"1\", \n" +
            "    \"laborCost\": \"30.00\", \n" +
            "    \"vinNo\": \"LSJA24U90KS160688\", \n" +
            "    \"couponCode\": \"\", \n" +
            "    \"supplierCode\": \"210079\", \n" +
            "    \"orderStatus\": \"0\", \n" +
            "    \"discount\": \"0.00\", \n" +
            "    \"reservationType\": \"1\", \n" +
            "    \"source\": \"NDMS\", \n" +
            "    \"ascShortName\": \"常熟申荣\", \n" +
            "    \"ascCity\": \"苏州市\", \n" +
            "    \"manHourTotalQuantity\": \"0.3\", \n" +
            "    \"ascFullName\": \"常熟申荣汽车销售服务有限公司\", \n" +
            "    \"partsTotalAmount\": \"117.00\", \n" +
            "    \"bookingType\": \"1\", \n" +
            "    \"operationTime\": \"2020-11-26 15:54:53\", \n" +
            "    \"addTime\": \"2020-11-26 17:23:49\"\n" +
            "}";
        System.out.println(JSON.toJSONString(orderInfo).toString());
    }


}
