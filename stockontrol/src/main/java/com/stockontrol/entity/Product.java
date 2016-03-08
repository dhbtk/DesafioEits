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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
	
	@NotNull
	@ManyToOne
	@Audited
	private Category category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Audited
	private List<Batch> batches = new ArrayList<Batch>();

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
