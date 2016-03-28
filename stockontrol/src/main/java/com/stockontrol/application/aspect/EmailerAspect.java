package com.stockontrol.application.aspect;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.stockontrol.domain.entity.User;

//@Aspect
public class EmailerAspect
{
	private MailSender mailSender;
	private SimpleMailMessage template;
	
	public MailSender getMailSender()
	{
		return mailSender;
	}

	public void setMailSender(MailSender mailSender)
	{
		this.mailSender = mailSender;
	}

	public SimpleMailMessage getTemplate()
	{
		return template;
	}

	public void setTemplate(SimpleMailMessage template)
	{
		this.template = template;
	}

//	@AfterReturning(pointcut = "com.stockontrol.domain.service.UserService.insert(com.stockontrol.domain.entity.User)", returning = "user")
	public void sendNewUserEmail(User user)
	{
		SimpleMailMessage msg = new SimpleMailMessage(template);
		msg.setTo(user.getEmail());
		msg.setText("Olá " + user.getFullName() + ", \r\n\r\n"
				+ "Seguem suas credenciais para acessar o controle de estoque.\r\n"
				+ "Email: " + user.getEmail() + "\r\n"
				+ "Senha: " + user.getPassword() + "\r\n");
		try
		{
			mailSender.send(msg);
		}
		catch(MailException ex)
		{
			System.err.println(ex.getMessage());
		}
	}
}
