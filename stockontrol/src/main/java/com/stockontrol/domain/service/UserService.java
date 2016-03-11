package com.stockontrol.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.expr.BooleanExpression;
import com.stockontrol.domain.entity.QUser;
import com.stockontrol.domain.entity.User;
import com.stockontrol.domain.repository.UserRepository;

@Service("userService")
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService
{
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
	{
		User user = userRepository.findByEmail(email);
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
	public Iterable<User> listAllByFilters(String fullName, String email, Boolean active, User.Profile profile)
	{
		QUser user = QUser.user;
		ArrayList<BooleanExpression> preds = new ArrayList<BooleanExpression>();
		BooleanExpression ex;
		int i;
		
		if(fullName == null && email == null && active == null && profile == null)
		{
			return userRepository.findAll();
		}
		if(fullName != null)
		{
			preds.add(user.fullName.containsIgnoreCase(fullName));
		}
		if(email != null)
		{
			preds.add(user.email.containsIgnoreCase(email));
		}
		if(active != null)
		{
			preds.add(user.active.eq(active));
		}
		if(profile != null)
		{
			preds.add(user.profile.eq(profile));
		}
		for(i = 1, ex = preds.get(0); i < preds.size(); i++)
		{
			ex = ex.and(preds.get(i));
		}
		return userRepository.findAll(ex);
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
