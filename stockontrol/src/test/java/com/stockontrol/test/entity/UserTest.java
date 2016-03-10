package com.stockontrol.test.entity;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stockontrol.entity.User;

import static junit.framework.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration("/jpaContext.xml")
public class UserTest
{
	@Autowired
	private Validator validator;
	
	@Test
	public void shouldHaveInvalidName()
	{
		User user = new User();
		Set<ConstraintViolation<User>> errors = validator.validate(user);
		for(ConstraintViolation<User> err: errors)
		{
			for(Path.Node n: err.getPropertyPath())
			{
				System.out.println(n.getName());
			}
			System.out.println(err.getMessage());
		}
		assertTrue(errors.size() > 0);
	}
	
	public void shouldHaveValidName()
	{
		
	}
	
	public void shouldHaveInvalidEmail()
	{
		
	}
	
	public void shouldHaveValidEmail()
	{
		
	}
}
