package com.stockontrol.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.annotations.Formula;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;

@Table(name = "categories")
@Entity
@DataTransferObject(javascript = "Category")
public class Category extends BaseEntity
{
	@Column(columnDefinition = "text")
	@Audited
	private String description;

	@NotBlank
	@Column(nullable = false, unique = true, length = 255)
	@Audited
	private String name;

	@Formula("(SELECT SUM((SELECT SUM(b.quantity) FROM batches b WHERE b.product_id = p.id)) FROM products p WHERE p.category_id = id)")
	private Long totalBatchCount;

	@Formula("(SELECT SUM((SELECT SUM(b.quantity) FROM batches b WHERE b.product_id = p.id AND "
			+ "DATE(b.expires_at) <= DATE(NOW()))) FROM products p WHERE p.category_id = id)")
	private Long totalExpiredBatchCount;

	@Formula("(SELECT SUM((SELECT SUM(b.quantity) FROM batches b WHERE b.product_id = p.id AND "
			+ " DATE(b.expires_at) > DATE(NOW()))) FROM products p WHERE p.category_id = id)")
	private Long totalExpiringBatchCount;

	@NotNull
	@Audited
	@ManyToOne
	private User user;
	
	public String getDescription()
	{
		return description;
	}
	public String getName()
	{
		return name;
	}

	public Long getTotalBatchCount()
	{
		return totalBatchCount;
	}

	public Long getTotalExpiredBatchCount()
	{
		return totalExpiredBatchCount;
	}

	public Long getTotalExpiringBatchCount()
	{
		return totalExpiringBatchCount;
	}

	public User getUser()
	{
		return user;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}
