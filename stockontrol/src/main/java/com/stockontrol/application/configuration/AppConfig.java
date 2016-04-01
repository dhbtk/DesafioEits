package com.stockontrol.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.stockontrol.application.aspect.EmailerAspect;

@Configuration
@ImportResource({"classpath:/dwr.xml", "classpath:/profiles.xml", "classpath:/security.xml" })
@ComponentScan({ "com.stockontrol.application.*", "com.stockontrol.domain.*" })
public class AppConfig
{
	@Autowired
	@Qualifier("mailSender")
	private JavaMailSender mailSender;
	
	@Bean
	public EmailerAspect emailerAspect()
	{
		EmailerAspect emailer = new EmailerAspect();
		emailer.setMailSender(mailSender);
		emailer.setTemplate(newUserMessageTemplate());
		return emailer;
	}
	
	@Bean
	public SimpleMailMessage newUserMessageTemplate()
	{
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("stockontrol@edanni.io");
		msg.setSubject("Sua nova conta");
		return msg;
	}
}
