package com.qimh.config;

import com.alibaba.fastjson.JSON;
import com.qimh.enumeration.Channel;
import com.qimh.enumeration.DynamicEnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private Environment environment;
    @Autowired
    private ChannelConfig channelConfig;
    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
        addChannel();
    }

    /**
     * 加载channel 枚举类型
     */
    public void addChannel() {
        System.out.println("channels:"+ JSON.toJSONString(channelConfig.getChannels()));

        for (int i = 0; i < channelConfig.getChannels().size(); i++) {
            String channel = channelConfig.getChannels().get(i);
            DynamicEnumUtils.addEnum(Channel.class, channel, new Class<?>[]{Integer.class, String.class},
                new Object[]{i, channel});
        }

    }
}
