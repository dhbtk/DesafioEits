package com.stockontrol.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.stockontrol.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User>
{
	public User findByEmail(String email);
}
