package com.stockontrol.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stockontrol.entity.User;
import com.stockontrol.service.UserService;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration("/jpaContext.xml")
public class UserServiceTest
{
	@Autowired
	private UserService userService;
	
	@Test
	public void shouldCreateUser()
	{
		User user = new User();
		user.setEmail("teste@teste.com");
		user.setActive(true);
		user.setFullName("Teste");
		user.setProfile(User.Profile.Administrator);
		user.setPassword("teste");
		
		User newUser = userService.save(user);
		
		assertNotNull(newUser.getId());
	}
}
