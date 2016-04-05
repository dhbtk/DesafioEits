package com.stockontrol.test.domain.entity;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

import com.stockontrol.domain.entity.Batch;
import com.stockontrol.domain.entity.User;

public class CopyPropertyTest
{
	@Test
	public void shouldCopyUserProperties()
	{
		User user1 = new User(), user2 = new User();
		
		String fullName = "Fulano de Tal";
		String email = "teste@teste.com";
		String password = "123456";
		Boolean active = true;
		User.Profile profile = User.Profile.Administrator;
		
		user1.setFullName(fullName);
		user1.setEmail(email);
		user1.setPassword(password);
		user1.setActive(active);
		user1.setProfile(profile);
		
		user1.copyNonNullPropertiesTo(user2);
		
		assertTrue(user2.getFullName().equals(fullName));
		assertTrue(user2.getEmail().equals(email));
		assertTrue(user2.getPassword().equals(password));
		assertTrue(user2.isActive() == active);
		assertTrue(user2.getProfile().equals(profile));
	}
	
	@Test
	public void shouldCopyBatchProperties()
	{
		Batch b1 = new Batch(), b2 = new Batch();
		String id = "1234";
		LocalDate ex = LocalDate.now();
		Long qtd = new Long(55);
		b1.setExpiresAt(ex);
		b1.setIdentifier(id);
		b1.setQuantity(qtd);
		b1.copyNonNullpropertiesTo(b2);
		
		assertTrue(b2.getExpiresAt().equals(ex));
		assertTrue(b2.getIdentifier().equals(id));
		assertTrue(b2.getQuantity().equals(qtd));
	}
	
	@Test
	public void shouldNotCopyId()
	{
		User user1 = new User(), user2 = new User();
		
		user1.setId(new Long(1));
		user1.copyNonNullpropertiesTo(user2);
		assertFalse(user1.getId().equals(user2.getId()));
	}
}
