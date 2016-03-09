package com.stockontrol.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockontrol.entity.Category;
import com.stockontrol.entity.Product;
import com.stockontrol.service.CategoryService;
import com.stockontrol.service.ProductService;

@RestController
@RequestMapping("/categories")
public class CategoriesController
{
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public List<Category> index(@RequestParam(required = false, value = "name") String name)
	{
		return categoryService.listAllByFilters(name);
	}

	@RequestMapping(value = "/{id}/products", method = RequestMethod.GET)
	public List<Product> listProducts(@PathVariable Long id)
	{
		return productService.listAllByFilters(id, null);
	}

	@RequestMapping(value = "/{id}/products", method = RequestMethod.POST)
	public Product insertProduct(@PathVariable Long id, @RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "price", required = true) BigDecimal price)
	{
		Product product = new Product();
		Category category = categoryService.find(id);
		product.setName(name);
		product.setPrice(price);
		product.setCategory(category);
		return productService.insert(product);
	}

	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public Category create(@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "description", required = false) String description)
	{
		Category category = new Category();
		category.setName(name);
		category.setDescription(description);
		return categoryService.insert(category);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Category show(@PathVariable Long id)
	{
		return categoryService.find(id);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.POST })
	public Category update(@PathVariable Long id, @RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "description", required = false) String description)
	{
		Category category = categoryService.find(id);
		if (name != null)
		{
			category.setName(name);
		}
		if (description != null)
		{
			category.setDescription(description);
		}
		return categoryService.update(category);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id)
	{
		categoryService.delete(id);
	}
}
