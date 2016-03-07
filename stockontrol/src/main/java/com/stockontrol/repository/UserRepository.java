package com.stockontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockontrol.entity.User;

public interface UserRepository extends JpaRepository<User, Long>
{
	public User findByEmail(String email);
}
