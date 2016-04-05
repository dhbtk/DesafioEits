package com.stockontrol.domain.entity;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.BeanUtils;

import java.util.Arrays;

@MappedSuperclass
public abstract class BaseEntity implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "serial")
	private Long id;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate()
	{
		createdAt = updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate()
	{
		updatedAt = LocalDateTime.now();
	}

	public LocalDateTime getCreatedAt()
	{
		return createdAt;
	}

	public LocalDateTime getUpdatedAt()
	{
		return updatedAt;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void copyNonNullpropertiesTo(BaseEntity target)
	{
		copyNonNullPropertiesTo(target, (String[]) null);
	}

	public void copyNonNullPropertiesTo(BaseEntity target, String... skip)
	{
		List<String> skipProps = new ArrayList<>();
		skipProps.add("id");
		skipProps.add("user");
		skipProps.add("createdAt");
		skipProps.add("updatedAt");

		if (skip != null)
		{
			skipProps.addAll(Arrays.asList(skip));
		}

		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(target.getClass());

		for (PropertyDescriptor pd : pds)
		{
			Method setter = pd.getWriteMethod();
			if (setter != null && !skipProps.contains(pd.getName()))
			{
				PropertyDescriptor src;
				if ((src = BeanUtils.getPropertyDescriptor(getClass(), pd.getName())) != null)
				{
					Method getter;
					if ((getter = src.getReadMethod()) != null)
					{
						try
						{
							Object val = getter.invoke(this);
							if (val != null)
							{
								setter.invoke(target, val);
							}
						} catch (Throwable ex)
						{
							throw new IllegalArgumentException(String.format("Não foi possível copiar a propriedade %s do objeto %s",
									pd.getName(), this.getClass().getName()));
						}
					}
				}
			}
		}
	}

}
