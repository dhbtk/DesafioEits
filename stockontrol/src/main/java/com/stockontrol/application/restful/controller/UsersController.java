package com.stockontrol.application.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	public Page<User> index(@RequestParam(name = "filter", required = false) String filter,
			@RequestParam(name = "active", required = false) Boolean active,
			@RequestParam(name = "profile", required = false) User.Profile profile,
			@RequestParam(name = "page", defaultValue = "0") int page)
	{
		SimplePageRequest pageRequest = new SimplePageRequest();
		pageRequest.setPage(page);
		pageRequest.setSize(20);
		return userService.listAllByFilters(filter, active, profile, pageRequest);
	}
	
	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public User create(@RequestBody User user)
	{
		user.setActive(true);
		return userService.insert(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User show(@PathVariable Long id)
	{
		return userService.find(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public User update(@RequestBody User sentUser, @PathVariable Long id)
	{
		User user = userService.find(id);
		
		sentUser.copyNonNullpropertiesTo(user);
		
		return userService.save(user);
	}
	
	@RequestMapping(value = "/{id}/activate", method = RequestMethod.POST)
	public User activate(@PathVariable Long id)
	{
		return userService.activate(userService.find(id));
	}
	
	@RequestMapping(value = "/{id}/deactivate", method = RequestMethod.POST)
	public User deactivate(@PathVariable Long id)
	{
		return userService.deactivate(userService.find(id));
	}
}
