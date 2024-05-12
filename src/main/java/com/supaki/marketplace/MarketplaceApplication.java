package com.supaki.marketplace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = {"com.supaki.marketplace"})
@ComponentScan("com.supaki.marketplace")
@EnableAspectJAutoProxy
@EnableCaching
@Slf4j
public class MarketplaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketplaceApplication.class, args);
		log.info("MARKETPLACE IS UP AND RUNNING");
	}

}
