package com.stockontrol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
	@RequestMapping(path = {"", "/"})
	public String getHome()
	{
		return "/resources/index.html";
	}
}
