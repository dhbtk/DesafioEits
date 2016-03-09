package com.stockontrol.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "products")
@Entity
public class Product extends BaseEntity
{
	
	@NotBlank
	@Column(nullable = false, unique = true, length = 255)
	@Audited
	private String name;
	
	@Min(0)
	@Column(nullable = false, precision = 16, scale = 2)
	@Audited
	private BigDecimal price;
	
	public String getName()
	{
		return name;
	}

	@ManyToOne
	@Audited
	private Category category;
	
	public Long getCategoryId()
	{
		return categoryId;
	}

	public void setCategoryId(Long categoryId)
	{
		this.categoryId = categoryId;
	}

	@Column(name = "category_id", insertable = false, updatable = false)
	private Long categoryId;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Audited
	private List<Batch> batches = new ArrayList<Batch>();
	
	@Formula("(select count(*) from batches b where b.product_id = id)")
	private Long batchCount;
	
	@Formula("(select count(*) from batches b where b.product_id = id and date(b.expires_at) > date(now()))")
	private Long expiringBatchCount;
	
	@Formula("(select count(*) from batches b where b.product_id = id and date(b.expires_at) <= date(now()))")
	private Long expiredBatchCount;

	public Long getBatchCount()
	{
		return batchCount;
	}

	public Long getExpiringBatchCount()
	{
		return expiringBatchCount;
	}

	public Long getExpiredBatchCount()
	{
		return expiredBatchCount;
	}

	public List<Batch> getBatches()
	{
		return batches;
	}

	public void setBatches(List<Batch> batches)
	{
		this.batches = batches;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	public Category getCategory()
	{
		return category;
	}

	public void setCategory(Category category)
	{
		this.category = category;
	}
	
}
