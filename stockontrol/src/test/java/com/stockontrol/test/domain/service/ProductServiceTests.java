package com.stockontrol.test.domain.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.stockontrol.domain.entity.Category;
import com.stockontrol.domain.service.ProductService;
import com.stockontrol.test.domain.AbstractIntegrationTests;

@DatabaseSetup({"/sampleUsers.xml", "/sampleCategories.xml", "/sampleProducts.xml"})
public class ProductServiceTests extends AbstractIntegrationTests
{
	@Autowired
	private ProductService productService;
	
	@Test
	public void shouldFindCategory()
	{
		Category c;
		c = productService.findCategory(new Long(1));
		assertNotNull(c);
		c = productService.findCategory(new Long(2));
		assertNotNull(c);
	}
	
	@Test
	public void shouldListCategoriesByFilters()
	{
		Page<Category> res;
		
		// Sem filtros
		res = productService.listAllCategoriesByFilters(null, null);
		assertTrue(res.getContent().size() == 2);
		
		// Por nome
		res = productService.listAllCategoriesByFilters("refri", null);
		assertTrue(res.getContent().size() == 1);
		res = productService.listAllCategoriesByFilters("salga", null);
		assertTrue(res.getContent().size() == 1);
		res = productService.listAllCategoriesByFilters("s", null);
		assertTrue(res.getContent().size() == 2);
	}
}
