package com.stockontrol.domain.service;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockontrol.domain.entity.QUser;
import com.stockontrol.domain.entity.User;
import com.stockontrol.domain.repository.UserRepository;
import com.stockontrol.domain.util.PredicateList;
import com.stockontrol.domain.util.SimplePageRequest;

@Service("userService")
@Transactional
@RemoteProxy(name = "userService")
public class UserService
{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

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
	public Page<User> listAllByFilters(String nameOrEmail, Boolean active, User.Profile profile, SimplePageRequest page)
	{
		QUser user = QUser.user;
		PredicateList predicates = new PredicateList();

		predicates
				.add(nameOrEmail,
						() -> user.fullName.containsIgnoreCase(nameOrEmail)
								.or(user.email.containsIgnoreCase(nameOrEmail)))
				.add(active, () -> user.active.eq(active)).add(profile, () -> user.profile.eq(profile));
		return userRepository.findAll(predicates.getIntersection(), page != null ? page.toPageRequest() : null);
	}

	@PreAuthorize("hasRole('USER')")
	@RemoteMethod
	public User getCurrent()
	{
		return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RemoteMethod
	public User save(User user)
	{
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
		user.setPasswordDigest(encoder.encode(user.getPassword()));
		return userRepository.saveAndFlush(user);
	}
}
