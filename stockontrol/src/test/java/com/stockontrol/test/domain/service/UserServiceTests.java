package com.stockontrol.test.domain.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.stockontrol.domain.entity.User;
import com.stockontrol.domain.service.UserService;
import com.stockontrol.test.domain.AbstractIntegrationTests;

@DatabaseSetup("/sampleUsers.xml")
public class UserServiceTests extends AbstractIntegrationTests
{
	@Autowired
	private UserService userService;
	
	@Test
	public void shouldDeactivateUser()
	{
		User user = userService.findByEmail("admin@teste.com");
		assertNotNull(user);
		
		user = userService.deactivate(user);
		
		assertFalse(user.isActive());
		
		user = userService.activate(user);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotDeactivateInactiveUser()
	{
		User user = userService.findByEmail("admin@teste.com");
		assertNotNull(user);
		assertTrue(user.isActive());
		
		user = userService.deactivate(user);
		
		user = userService.deactivate(user); // kabum
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotActivateActiveUser()
	{
		User user = userService.findByEmail("admin@teste.com");
		assertNotNull(user);
		assertTrue(user.isActive());
		user = userService.activate(user); // explode joga arremessa
	}
	
	@Test
	public void shouldActivateUser()
	{
		User user = userService.findByEmail("admin@teste.com");
		assertNotNull(user);
		if(user.isActive())
		{
			user = userService.deactivate(user);
		}
		
		user = userService.activate(user);
		
		assertTrue(user.isActive());
	}
	
	@Test
	public void shouldFindByEmail()
	{
		assertNotNull(userService.findByEmail("admin@teste.com"));
		assertNotNull(userService.findByEmail("user@teste.com"));
	}
	
	@Test
	public void shouldHavePasswordDigest()
	{
		User user = userService.findByEmail("admin@teste.com");
		assertNotNull(user);
		assertNotNull(user.getPasswordDigest());
		assertFalse(user.getPasswordDigest().isEmpty());
	}
	
	@Test
	public void shouldHaveUnchangedPasswordDigest()
	{
		User user = userService.findByEmail("admin@teste.com");
		String digest = user.getPasswordDigest();
		user.setFullName("Admin");
		user = userService.save(user);
		
		assertTrue(digest.equals(user.getPasswordDigest()));
	}
	
	@Test
	public void shouldHaveChangedPasswordDigest()
	{
		User user = userService.findByEmail("admin@teste.com");
		String digest = user.getPasswordDigest();
		user.setPassword("admin");
		user = userService.save(user);
		assertTrue(user.getPassword() == null);
		assertFalse(digest.equals(user.getPasswordDigest()));
	}
	
	@Test
	public void shouldFindByFilters()
	{
		Page<User> res;
		
		// primeiro por email
		res = userService.listAllByFilters("@teste.com", null, null, null);
		assertNotNull(res);
		assertTrue(res.getContent().size() == 2);
		
		// por nome
		res = userService.listAllByFilters("usuário", null, null, null);
		assertNotNull(res);
		assertTrue(res.getContent().size() == 1);
		
		// ativo
		userService.deactivate(userService.findByEmail("admin@teste.com"));
		res = userService.listAllByFilters(null, false, null, null);
		assertNotNull(res);
		assertTrue(res.getContent().size() == 1);
		
		userService.activate(userService.findByEmail("admin@teste.com"));
		res = userService.listAllByFilters(null, true, null, null);
		assertNotNull(res);
		assertTrue(res.getContent().size() == 2);
		
		// perfil
		res = userService.listAllByFilters(null, null, User.Profile.Administrator, null);
		assertNotNull(res);
		assertTrue(res.getContent().size() == 1);
		
		res = userService.listAllByFilters(null, null, User.Profile.User, null);
		assertNotNull(res);
		assertTrue(res.getContent().size() == 1);
	}
	
	@Test
	public void shouldFindUser()
	{
		User user = userService.find(new Long(1));
		assertNotNull(user);
	}
	
	@Test
	public void shouldInsertUser()
	{
		User user = new User();
		user.setEmail("teste@usuario.com");
		user.setFullName("Teste");
		user.setPassword("umdoistresquatro");
		user.setActive(true);
		user.setProfile(User.Profile.User);
		
		user = userService.insert(user);
		
		assertNotNull(user);
	}
}
