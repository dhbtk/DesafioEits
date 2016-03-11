package com.stockontrol.domain.service;

import java.util.ArrayList;

import javax.validation.Valid;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.expr.BooleanExpression;
import com.stockontrol.domain.entity.QUser;
import com.stockontrol.domain.entity.User;
import com.stockontrol.domain.repository.UserRepository;

@Service("userService")
@Transactional
@RemoteProxy(name = "userService")
public class UserService
{
	@Autowired
	private UserRepository userRepository;

	@PreAuthorize("hasRole('ADMIN')")
	@RemoteMethod
	public User activate(User user)
	{
		if (user.isActive())
		{
			throw new IllegalArgumentException("O usuário já está ativo.");
		}
		user.setActive(true);
		return save(user);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RemoteMethod
	public User deactivate(User user)
	{
		if (!user.isActive())
		{
			throw new IllegalArgumentException("O usuário já está inativo.");
		}
		user.setActive(false);
		return save(user);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RemoteMethod
	public User find(Long id)
	{
		return userRepository.findOne(id);
	}

	@PreAuthorize("hasRole('USER')")
	@RemoteMethod
	public Iterable<User> listAllByFilters(String fullName, String email, Boolean active, User.Profile profile)
	{
		QUser user = QUser.user;
		ArrayList<BooleanExpression> preds = new ArrayList<BooleanExpression>();
		BooleanExpression ex;
		int i;

		if (fullName == null && email == null && active == null && profile == null)
		{
			return userRepository.findAll();
		}
		if (fullName != null)
		{
			preds.add(user.fullName.containsIgnoreCase(fullName));
		}
		if (email != null)
		{
			preds.add(user.email.containsIgnoreCase(email));
		}
		if (active != null)
		{
			preds.add(user.active.eq(active));
		}
		if (profile != null)
		{
			preds.add(user.profile.eq(profile));
		}
		for (i = 1, ex = preds.get(0); i < preds.size(); i++)
		{
			ex = ex.and(preds.get(i));
		}
		return userRepository.findAll(ex);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RemoteMethod
	public User save(User user)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (user.getPassword() != null)
		{
			user.setPasswordDigest(encoder.encode(user.getPassword()));
		}
		return userRepository.saveAndFlush(user);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RemoteMethod
	public User insert(User user)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return userRepository.saveAndFlush(user);
	}
}
