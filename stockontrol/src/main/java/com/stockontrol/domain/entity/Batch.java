package com.stockontrol.domain.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;

@Table(name = "batches")
@Entity
@DataTransferObject(javascript = "Batch")
public class Batch extends BaseEntity
{
	@NotNull
	@Audited
	@Column(name = "expires_at", nullable = false)
	private LocalDate expiresAt;

	@NotBlank
	@Audited
	@Column(nullable = false, length = 255)
	private String identifier;

	@NotNull
	@Audited
	@Column(name = "manufactured_at", nullable = false)
	private LocalDate manufacturedAt;

	@NotNull
	@Audited
	@ManyToOne
	private Product product;

	@Min(1)
	@Audited
	@Column(nullable = false)
	private Long quantity;

	@NotNull
	@Audited
	@ManyToOne
	private User user;

	public LocalDate getExpiresAt()
	{
		return expiresAt;
	}

	public String getIdentifier()
	{
		return identifier;
	}

	public LocalDate getManufacturedAt()
	{
		return manufacturedAt;
	}

	public Product getProduct()
	{
		return product;
	}

	public Long getQuantity()
	{
		return quantity;
	}

	public User getUser()
	{
		return user;
	}
	
	public boolean isExpired()
	{
		return LocalDate.now().isAfter(expiresAt);
	}

	@AssertTrue
	public boolean isValidRange()
	{
		return this.manufacturedAt != null && this.expiresAt != null
				&& this.expiresAt.compareTo(this.manufacturedAt) >= 0;
	}

	public void setExpiresAt(LocalDate expiresAt)
	{
		this.expiresAt = expiresAt;
	}

	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}

	public void setManufacturedAt(LocalDate manufacturedAt)
	{
		this.manufacturedAt = manufacturedAt;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}

	public void setQuantity(Long quantity)
	{
		this.quantity = quantity;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}
