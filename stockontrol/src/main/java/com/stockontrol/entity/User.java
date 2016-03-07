package com.stockontrol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

@Table(name = "users")
@Entity
public class User extends BaseEntity
{
	public enum Profile
	{
		User, Administrator;
	}
	
	@Column(name = "full_name", nullable = false, length = 255)
	@Audited
	private String fullName;
	@Column(nullable = false, unique = true, length = 255)
	@Audited
	private String email;
	@Column(name = "password_digest", nullable = false, length = 255)
	@Audited
	private String passwordDigest;
	@Column(nullable = false)
	@Audited
	private Profile profile;
	@Column(nullable = false)
	@Audited
	private boolean active;
	@Transient
	private String password;
	
	
	public String getFullName()
	{
		return fullName;
	}
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPasswordDigest()
	{
		return passwordDigest;
	}
	public void setPasswordDigest(String passwordDigest)
	{
		this.passwordDigest = passwordDigest;
	}
	public Profile getProfile()
	{
		return profile;
	}
	public void setProfile(Profile profile)
	{
		this.profile = profile;
	}
	public boolean isActive()
	{
		return active;
	}
	public void setActive(boolean active)
	{
		this.active = active;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword()
	{
		return password;
	}
	
}
