package com.stockontrol.application.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

public class AuthenticationFailureHandler
		implements org.springframework.security.web.authentication.AuthenticationFailureHandler
{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException
	{
		response.setContentType("text/plain; charset=utf-8");
		if(exception instanceof BadCredentialsException)
		{
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
		}
		else if(exception instanceof AccountStatusException)
		{
			response.sendError(HttpServletResponse.SC_FORBIDDEN, exception.getMessage());
		}
	}
}
