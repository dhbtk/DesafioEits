package com.stockontrol.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.envers.Audited;

@Entity
public class Category
{
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique = true, length = 255)
	@Audited
	private String name;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	@Audited
	private List<Product> Products = new ArrayList<>();

	public void setProducts(List<Product> products)
	{
		Products = products;
	}

	public List<Product> getProducts()
	{
		return Products;
	}

	public Long getId()
	{
		return id;
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
}
