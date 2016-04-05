package com.stockontrol.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stockontrol.domain.entity.User;
import com.stockontrol.domain.repository.UserRepository;

@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService
{
	@Autowired
	private UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
	{
		User user = userRepository.findByEmail(email);
		if (user == null)
		{
			throw new UsernameNotFoundException("Email não encontrado: " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPasswordDigest(),
				user.getActive(), true, true, true, getUserAuthorities(user));
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
