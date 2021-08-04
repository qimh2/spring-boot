package com.qimh.sign;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestDemo {

    private static final String uri = "/gw/spCoupon/fetchHubCouponV2/1.0";
    private static final String userKey = "coupon";
    private static final String userSecret = "2d223e65fb3a4273a7ab3ffc15a44ed4";
    private static final String host = "https://oapi-qa.ebanma.com:20016";

    public static void main(String[] args) {
        //请求入参转换成字符串
//        String reqParam = "{\"epCreateOrderDto\":{\"addTime\":\"2020-09-27 17:17:03\",\"brandCode\":4,\"businessOrderId\":\"O202009271717025840\",\"businessStatus\":\"1\",\"buyerId\":\"Rt4Req-PIDzjREAmOUPI5A\",\"buyerName\":\"\",\"couponIds\":[],\"createPayOrder\":false,\"dealType\":\"品位\",\"goodsAmount\":\"55.00\",\"isShow\":1,\"orderAmount\":\"45.80\",\"orderCatId\":\"300102\",\"orderGoods\":[{\"goodsDesc\":\"800g\",\"goodsId\":\"160119826698091084\",\"goodsName\":\"印尼进口 鲜虾片 原味500g（需油炸）\",\"goodsPrice\":\"55.00\",\"goodsUrl\":\"https://jftd-picture.oss-cn-shanghai.aliyuncs.com/202002221019330667.jpg\",\"quantity\":1,\"unit\":\"RMB\"}],\"orderStatus\":\"1004\",\"payOnlineOffline\":\"1\",\"pointsValue\":\"45.80\",\"postage\":\"0.00\",\"price\":\"45.80\",\"remark\":\"\",\"totalQuantity\":1,\"vin\":\"\"}}";
        String reqParam = "{\"fetchDto\":{\"userId\":\"19780717821533\",\"couponId\":1717,\"platform\":0,"
                + "\"quantity\":1}}";
        String now = String.valueOf(System.currentTimeMillis());
        //1、获取加签内容
        String signContent = getSignContent(reqParam, now);
        //2、获取签名
        //HMACSHA1算法加密
        byte[] bytes = hmacSha1(signContent, userSecret);
        //base64编码
        String sign = Base64.encodeBase64URLSafeString(bytes);
        HttpRequest request = HttpUtil.createPost(host + uri)
                .header("gw-user-key", userKey)
                .header("gw-timestamp", now)
                .header("gw-sign", sign);
        HttpResponse execute = request.body(reqParam).execute();
        System.out.println(execute.body());
    }

    private static String getSignContent(String reqParam, String now) {
        //将以上字符串进行正序排序
        List list = new ArrayList();
        list.add(reqParam);
        list.add(now);
        list.add(userKey);
        list.add(uri);
        //正序排序
        Collections.sort(list);

        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(str -> {
            stringBuilder.append(str);
        });
        //拼接成字符串
        return stringBuilder.toString();
    }

    public static byte[] hmacSha1(String src, String key) {
        try {
            //使用utf-8字符集
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("utf-8"), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(src.getBytes("utf-8"));
            return rawHmac;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
