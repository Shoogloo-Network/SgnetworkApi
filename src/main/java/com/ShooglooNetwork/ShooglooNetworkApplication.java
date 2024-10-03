package com.ShooglooNetwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@EnableTransactionManagement
@EnableAutoConfiguration
//@PropertySource("file:/opt/work/property/sgNetwork.properties")
@SpringBootApplication
public class ShooglooNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShooglooNetworkApplication.class, args);
	}

}
