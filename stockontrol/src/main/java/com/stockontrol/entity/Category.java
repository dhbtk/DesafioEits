package com.stockontrol.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category extends BaseEntity
{	
	@Column(nullable = false, unique = true, length = 255)
	@Audited
	private String name;
	
	@Column(columnDefinition = "text")
	@Audited
	private String text;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Audited
	private List<Product> Products = new ArrayList<>();

	public String getName()
	{
		return name;
	}

	public List<Product> getProducts()
	{
		return Products;
	}

	public String getText()
	{
		return text;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setProducts(List<Product> products)
	{
		Products = products;
	}

	public void setText(String text)
	{
		this.text = text;
	}
}
