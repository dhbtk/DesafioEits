package com.stockontrol.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "batches")
@Entity
public class Batch extends BaseEntity
{
	@NotBlank
	@Audited
	@Column(nullable = false, length = 255)
	private String identifier;
	
	@NotNull
	@Audited
	@Column(name = "manufactured_at", nullable = false)
	private Date manufacturedAt;
	
	@NotNull
	@Audited
	@Column(name = "expires_at", nullable = false)
	private Date expiresAt;
	
	@Min(1)
	@Audited
	@Column(nullable = false)
	private Long quantity;
	
	@NotNull
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
	
	@JsonIgnore
	@AssertTrue
	public boolean isValidRange()
	{
		return this.manufacturedAt != null && this.expiresAt != null && this.expiresAt.compareTo(this.manufacturedAt) >= 0;
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
