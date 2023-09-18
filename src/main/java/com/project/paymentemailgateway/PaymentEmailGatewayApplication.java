package com.project.paymentemailgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class})
public class PaymentEmailGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentEmailGatewayApplication.class, args);
	}

}
