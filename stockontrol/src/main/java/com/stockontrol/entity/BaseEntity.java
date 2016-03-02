package com.stockontrol.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public abstract class BaseEntity
{
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "created_at", nullable = false)
	private Date createdAt;
	
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate()
	{
		createdAt = updatedAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate()
	{
		updatedAt = new Date();
	}

	public Date getCreatedAt()
	{
		return createdAt;
	}

	public Date getUpdatedAt()
	{
		return updatedAt;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	
	
}
