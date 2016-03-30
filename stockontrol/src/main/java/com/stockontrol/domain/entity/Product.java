package com.stockontrol.domain.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;

@Table(name = "products")
@Entity
@DataTransferObject(javascript = "Product")
public class Product extends BaseEntity
{

	@Formula("(select sum(b.quantity) from batches b where b.product_id = id)")
	private Long batchCount;

	@NotNull
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Audited
	private Category category;

	@Formula("(select sum(b.quantity) from batches b where b.product_id = id and date(b.expires_at) <= date(now()))")
	private Long expiredBatchCount;

	@Formula("(select sum(b.quantity) from batches b where b.product_id = id and date(b.expires_at) > date(now()))")
	private Long expiringBatchCount;

	@NotBlank
	@Column(nullable = false, unique = true, length = 255)
	@Audited
	private String name;

	@Min(0)
	@Column(nullable = false, precision = 16, scale = 2)
	@Audited
	private BigDecimal price;

	@NotNull
	@Audited
	@ManyToOne
	private User user;

	public Long getBatchCount()
	{
		return batchCount;
	}

	public Category getCategory()
	{
		return category;
	}

	public Long getExpiredBatchCount()
	{
		return expiredBatchCount;
	}

	public Long getExpiringBatchCount()
	{
		return expiringBatchCount;
	}

	public String getName()
	{
		return name;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public User getUser()
	{
		return user;
	}

	public void setCategory(Category category)
	{
		this.category = category;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

}
