package com.stockontrol.application.configuration;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.stockontrol.test.MockJavaMailSender;

@Configuration
@ImportResource({"classpath:/dwr.xml", "classpath:/security.xml" })
@ComponentScan({ "com.stockontrol.application.*", "com.stockontrol.domain.*" })
@EnableAspectJAutoProxy
public class AppConfig
{	
	@Bean
	public SimpleMailMessage newUserMessageTemplate()
	{
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("stockontrol@edanni.io");
		msg.setSubject("Sua nova conta");
		return msg;
	}
	
	@Profile("default")
	@Bean
	public Clock defaultClock()
	{
		return Clock.systemDefaultZone();
	}
	
	@Profile("default")
	@Bean
	public JavaMailSender defaultMailSender()
	{
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(System.getenv("MAIL_HOST"));
		sender.setPort(587);
		sender.setUsername(System.getenv("MAIL_USERNAME"));
		sender.setPassword(System.getenv("MAIL_PASSWORD"));
		sender.setProtocol("smtp");
		
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.quitwait", "false");
		
		sender.setJavaMailProperties(props);
		
		return sender;
	}
	
	@Profile("test")
	@Bean
	public Clock testClock()
	{
		return Clock.fixed(Instant.parse("2016-03-30T15:00:00.00Z"), ZoneId.of("America/Sao_Paulo"));
	}
	
	@Profile("test")
	@Bean
	public JavaMailSender testMailSender()
	{
		return new MockJavaMailSender();
	}
}
