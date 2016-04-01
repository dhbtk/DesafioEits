package com.stockontrol.application.configuration;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:/dwr.xml", "classpath:/email.xml", "classpath:/security.xml" })
@ComponentScan({ "com.stockontrol.application.*", "com.stockontrol.domain.*" })
public class AppConfig
{
	@Bean
	public Clock clock()
	{
		return Clock.systemDefaultZone();
	}
}
