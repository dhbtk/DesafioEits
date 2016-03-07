package com.stockontrol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.envers.Audited;

@Entity
public class User extends BaseEntity
{
	public enum Profile
	{
		User, Administrator;
	}
	
	@Column(nullable = false, length = 255)
	@Audited
	private String fullName;
	@Column(nullable = false, unique = true, length = 255)
	@Audited
	private String email;
	@Column(nullable = false, length = 255)
	@Audited
	private String passwordDigest;
	@Column(nullable = false)
	@Audited
	private Profile profile;
	@Column(nullable = false)
	@Audited
	private boolean active;
	
	
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
}
