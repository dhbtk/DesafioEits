package com.stockontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockontrol.entity.User;
import com.stockontrol.repository.UserRepository;

@Service("userService")
public class UserService
{
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User findByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional
	public User activate(User user)
	{
		if(user.isActive())
		{
			throw new IllegalArgumentException("O usuário já está ativo.");
		}
		user.setActive(true);
		return save(user);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional
	public User deactivate(User user)
	{
		if(!user.isActive())
		{
			throw new IllegalArgumentException("O usuário já está inativo.");
		}
		user.setActive(false);
		return save(user);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional
	public User find(Long id)
	{
		return userRepository.findOne(id);
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public List<User> findAll()
	{
		return userRepository.findAll();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional
	public User save(User user)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(user.getPassword() != null)
		{
			user.setPasswordDigest(encoder.encode(user.getPassword()));
		}
		return userRepository.saveAndFlush(user);
	}
}
