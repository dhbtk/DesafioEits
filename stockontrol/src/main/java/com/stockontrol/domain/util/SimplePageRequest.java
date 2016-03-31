package com.stockontrol.domain.util;

import java.io.Serializable;

import org.directwebremoting.annotations.DataTransferObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

/**
 * Abstração simplificada de PageRequest para facilitar o uso de paginação via
 * DWR.
 * 
 * @author diana
 *
 */
@DataTransferObject(javascript = "SimplePageRequest")
public class SimplePageRequest implements Serializable
{
	@DataTransferObject(type = "enum")
	public enum Direction
	{
		ASC, DESC;
	}

	private static final long serialVersionUID = -4541509938956089562L;

	private int page;
	private int size;
	private String property;
	private Direction direction;
	
	public static SimplePageRequest fromPageRequest(PageRequest p)
	{
		SimplePageRequest spr = new SimplePageRequest();
		spr.setPage(p.getPageNumber());
		spr.setSize(p.getPageSize());
		if(p.getSort() != null)
		{
			for(Order order: p.getSort())
			{
				spr.setProperty(order.getProperty());
				spr.setDirection(order.getDirection() == Sort.Direction.ASC ? Direction.ASC : Direction.DESC);
				break;
			}
		}
		return spr;
	}
	
	public SimplePageRequest()
	{
		
	}
	
	public SimplePageRequest(int page, int size)
	{
		this.page = page;
		this.size = size;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public String getProperty()
	{
		return property;
	}

	public void setProperty(String property)
	{
		this.property = property;
	}

	public Direction getDirection()
	{
		return direction;
	}

	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}
	
	public static PageRequest toPageRequest(SimplePageRequest page)
	{
		Sort sort;
		if(page == null)
		{
			return null;
		}
		if(page.getDirection() != null && page.getProperty() != null)
		{
			sort = new Sort(new Order(page.getDirection() == SimplePageRequest.Direction.ASC ? Sort.Direction.ASC : Sort.Direction.DESC, page.getProperty()));
		}
		else
		{
			sort = null;
		}
		return new PageRequest(page.getPage(), page.getSize(), sort);
	}
}
