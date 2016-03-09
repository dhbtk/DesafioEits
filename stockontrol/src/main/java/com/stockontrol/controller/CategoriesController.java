package com.stockontrol.controller;

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
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public List<Category> index(@RequestParam(required = false, value = "name") String name)
	{
		return categoryService.listAllByFilters(name);
	}
	
	@RequestMapping(value = "/{id}/products", method = RequestMethod.GET)
	public List<Product> listProducts(@PathVariable Long id)
	{
		return productService.findAllByCategoryId(id);
	}
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
	public Category create(@RequestParam(name = "name", required = true) String name, @RequestParam(name = "description", required = false) String description)
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
	
	@RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
	public Category update(@PathVariable Long id,@RequestParam("name") String name,@RequestParam("description") String description)
	{
		Category category = categoryService.find(id);
		if(name != null)
		{
			category.setName(name);
		}
		if(description != null)
		{
			category.setDescription(description);
		}
		return categoryService.update(category);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id)
	{
		Category category = categoryService.find(id);
		categoryService.delete(category);
	}
}
