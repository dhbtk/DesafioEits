package com.stockontrol.test.domain.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.stockontrol.domain.entity.Category;
import com.stockontrol.domain.service.ProductService;
import com.stockontrol.domain.service.UserService;
import com.stockontrol.test.domain.AbstractIntegrationTests;

@DatabaseSetup({"/sampleUsers.xml", "/sampleCategories.xml", "/sampleProducts.xml"})
public class ProductServiceTests extends AbstractIntegrationTests
{
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	
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
	
	@Test
	public void shouldInsertCategory()
	{
		Category c = new Category();
		c.setName("Doces");
		c.setDescription("Coisas doces de comer");
		c.setUser(userService.find(new Long(1)));
		
		c = productService.insertCategory(c);
		assertNotNull(c.getId());
	}
	
	@Test
	public void shouldUpdateCategory()
	{
		Category c = productService.findCategory(new Long(1));
		String newDescription = "Refrigerantes, coisas boas de beber só que nem tanto";
		c.setDescription(newDescription);
		
		c = productService.updateCategory(c);
		
		assertTrue(newDescription.equals(c.getDescription()));
	}
	
	@Test
	public void shouldDeleteCategory()
	{
		Long id = new Long(2);
		productService.deleteCategory(id);
		Category c = productService.findCategory(id);
		assertTrue(c == null);
	}
}
