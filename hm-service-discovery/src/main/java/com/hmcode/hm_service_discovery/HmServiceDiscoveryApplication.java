package com.hmcode.hm_service_discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HmServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmServiceDiscoveryApplication.class, args);
	}

}
