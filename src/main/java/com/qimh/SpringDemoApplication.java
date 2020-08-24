package com.qimh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SpringDemoApplication.class);
		//禁用命令行
		springApplication.setAddCommandLineProperties(false);
		springApplication.run(args);
		//普通启动方法
//		SpringApplication.run(SpringDemoApplication.class, args);
	}

}
