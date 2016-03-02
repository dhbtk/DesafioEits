package com.stockontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockontrol.entity.Category;
import com.stockontrol.repository.CategoryRepository;

@Service("categoryService")
public class CategoryService
{
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional
	public List<Category> findAll()
	{
		return categoryRepository.findAll();
	}
	
	@Transactional
	public Category find(Long id)
	{
		return categoryRepository.findOne(id);
	}

	@Transactional
	public Category save(Category category)
	{
		return categoryRepository.saveAndFlush(category);
	}
	
	@Transactional
	public void delete(Category category)
	{
		categoryRepository.delete(category);
	}
}
