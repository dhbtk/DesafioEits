package com.stockontrol.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "categories")
@Entity
public class Category extends BaseEntity
{
	@NotBlank
	@Column(nullable = false, unique = true, length = 255)
	@Audited
	private String name;

	@Column(columnDefinition = "text")
	@Audited
	private String description;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Audited
	private List<Product> Products = new ArrayList<>();

	@Formula("(SELECT SUM((SELECT COUNT(*) FROM batches b WHERE b.product_id = p.id)) FROM products p WHERE p.category_id = id)")
	private Long totalBatchCount;
	@Formula("(SELECT SUM((SELECT COUNT(*) FROM batches b WHERE b.product_id = p.id AND "
			+ " DATE(b.expires_at) > DATE(NOW()))) FROM products p WHERE p.category_id = id)")
	private Long totalExpiringBatchCount;
	@Formula("(SELECT SUM((SELECT COUNT(*) FROM batches b WHERE b.product_id = p.id AND "
			+ "DATE(b.expires_at) <= DATE(NOW()))) FROM products p WHERE p.category_id = id)")
	private Long totalExpiredBatchCount;

	public Long getTotalBatchCount()
	{
		return totalBatchCount;
	}

	public Long getTotalExpiringBatchCount()
	{
		return totalExpiringBatchCount;
	}

	public Long getTotalExpiredBatchCount()
	{
		return totalExpiredBatchCount;
	}

	public String getName()
	{
		return name;
	}

	@JsonIgnore
	public List<Product> getProducts()
	{
		return Products;
	}

	public String getDescription()
	{
		return description;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setProducts(List<Product> products)
	{
		Products = products;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}
