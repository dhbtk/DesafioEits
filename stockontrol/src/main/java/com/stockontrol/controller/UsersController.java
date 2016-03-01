package com.stockontrol.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController
{
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable String id)
	{
		String result = "Usuário com id " + id;
		return result;
	}
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String listUsers()
	{
		return "teste";
	}
}
