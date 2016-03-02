package com.stockontrol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockontrol.entity.Category;
import com.stockontrol.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoriesController
{
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public List<Category> index()
	{
		return categoryService.findAll();
	}
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
	public Category create(@RequestParam(name = "name", required = true) String name)
	{
		Category category = new Category();
		category.setName(name);
		return categoryService.save(category);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Category show(@PathVariable Long id)
	{
		return categoryService.find(id);
	}
	
	
}
