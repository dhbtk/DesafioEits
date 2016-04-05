package com.stockontrol.application.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.stockontrol.domain.entity.User;

@Component
@Aspect
public class EmailerAspect
{
	@Autowired
	private MailSender mailSender;
	@Autowired
	private SimpleMailMessage template;

	@AfterReturning(pointcut = "target(com.stockontrol.domain.service.UserService) && execution(com.stockontrol.domain.entity.User insert(com.stockontrol.domain.entity.User))", returning = "user")
	public void sendNewUserEmail(User user)
	{
		SimpleMailMessage msg = new SimpleMailMessage(template);
		msg.setTo(user.getEmail());
		msg.setText("Olá " + user.getFullName() + ", \r\n\r\n"
				+ "Seguem suas credenciais para acessar o controle de estoque.\r\n" + "Email: " + user.getEmail()
				+ "\r\n" + "Senha: " + user.getPassword() + "\r\n");
		try
		{
			mailSender.send(msg);
		} catch (MailException ex)
		{
			System.err.println(ex.getMessage());
		}
	}
}
