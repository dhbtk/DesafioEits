package com.stockontrol.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.envers.Audited;

@Entity
public class Product
{
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique = true, length = 255)
	@Audited
	private String name;
	
	@Column(nullable = false, precision = 16, scale = 2)
	@Audited
	private BigDecimal price;
	
	@ManyToOne
	@Audited
	private Category category;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@Audited
	private List<Batch> batches = new ArrayList<Batch>();

	public Long getId()
	{
		return id;
	}

	public List<Batch> getBatches()
	{
		return batches;
	}

	public void setBatches(List<Batch> batches)
	{
		this.batches = batches;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
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
