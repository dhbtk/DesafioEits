package com.stockontrol.application.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockontrol.domain.entity.User;
import com.stockontrol.domain.service.UserService;
import com.stockontrol.domain.util.SimplePageRequest;

@RestController
@RequestMapping("/api/users")
public class UsersController
{
	@Autowired
	private UserService userService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public Iterable<User> listUsers(@RequestParam(name = "filter", required = false) String filter,
			@RequestParam(name = "active", required = false) Boolean active,
			@RequestParam(name = "profile", required = false) User.Profile profile,
			@RequestParam(name = "page", defaultValue = "0") int page)
	{
		SimplePageRequest pageRequest = new SimplePageRequest();
		pageRequest.setPage(page);
		pageRequest.setSize(20);
		return userService.listAllByFilters(filter, active, profile, pageRequest);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable String id)
	{
		String result = "Usuário com id " + id;
		return result;
	}
}
