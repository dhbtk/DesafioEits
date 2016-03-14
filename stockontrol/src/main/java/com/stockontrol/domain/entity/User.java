package com.stockontrol.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Table(name = "users")
@Entity
@DataTransferObject(javascript = "User")
public class User extends BaseEntity
{
	@DataTransferObject(type = "enum")
	public enum Profile
	{
		User, Administrator;
	}
	
	@NotBlank
	@Column(name = "full_name", nullable = false, length = 255)
	@Audited
	private String fullName;
	
	@NotBlank
	@Email
	@Column(nullable = false, unique = true, length = 255)
	@Audited
	private String email;
	
	@Column(name = "password_digest", nullable = false, length = 255)
	@Audited
	private String passwordDigest;
	
	@NotNull
	@Column(nullable = false)
	@Audited
	private Profile profile;
	
	@NotNull
	@Column(nullable = false)
	@Audited
	private Boolean active;
	
	@NotBlank
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
