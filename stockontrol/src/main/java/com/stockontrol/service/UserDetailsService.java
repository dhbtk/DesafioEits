package com.stockontrol.service;

import org.springframework.stereotype.Service;

import com.stockontrol.entity.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService
{
	@Autowired
	private UserService userService;

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
	{
		User user = userService.findByEmail(email);
		if (user == null)
		{
			throw new UsernameNotFoundException("Email não encontrado: " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPasswordDigest(),
				user.isActive(), true, true, true, getUserAuthorities(user));
	}

	private List<GrantedAuthority> getUserAuthorities(User user)
	{
		List<GrantedAuthority> roles = new ArrayList<>();
		if(user.getProfile() == User.Profile.Administrator)
		{
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		return roles;
	}
}
