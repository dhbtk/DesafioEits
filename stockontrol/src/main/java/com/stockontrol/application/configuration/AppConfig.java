package com.stockontrol.application.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:/security.xml", "classpath:/dwr.xml", "classpath:/email.xml",
		"classpath:/appConfig.xml" })
@ComponentScan({"com.stockontrol.application.*", "com.stockontrol.domain.*"})
public class AppConfig
{
	
}
