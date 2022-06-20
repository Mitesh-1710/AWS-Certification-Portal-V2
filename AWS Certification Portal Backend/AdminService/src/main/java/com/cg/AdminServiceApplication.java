package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class AdminServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdminServiceApplication.class, args);
	}
}
