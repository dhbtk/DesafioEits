package com.stockontrol.application.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ImportResource({"classpath:/security.xml", "classpath:/dwr.xml", "classpath:/email.xml",
		"classpath:/appConfig.xml" })
@ComponentScan({"com.stockontrol.application.*"})
@EnableJpaRepositories("com.stockontrol.domain.repository")
@EnableTransactionManagement
@EnableWebMvc
public class AppConfig
{
	
}
