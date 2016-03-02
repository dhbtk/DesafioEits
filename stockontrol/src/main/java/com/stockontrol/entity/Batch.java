package com.stockontrol.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;

@Entity
public class Batch extends BaseEntity
{
	@Audited
	@Column(nullable = false, length = 255)
	private String identifier;
	
	@Audited
	@Column(name = "manufactured_at", nullable = false)
	private Date manufacturedAt;
	
	@Audited
	@Column(name = "expires_at", nullable = false)
	private Date expiresAt;
	
	@Audited
	@Column(nullable = false)
	private Long quantity;
	
	@Audited
	@ManyToOne
	private Product product;

	public String getIdentifier()
	{
		return identifier;
	}

	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}

	public Date getManufacturedAt()
	{
		return manufacturedAt;
	}

	public void setManufacturedAt(Date manufacturedAt)
	{
		this.manufacturedAt = manufacturedAt;
	}

	public Date getExpiresAt()
	{
		return expiresAt;
	}

	public void setExpiresAt(Date expiresAt)
	{
		this.expiresAt = expiresAt;
	}

	public Long getQuantity()
	{
		return quantity;
	}

	public void setQuantity(Long quantity)
	{
		this.quantity = quantity;
	}

	public Product getProduct()
	{
		return product;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}
}
