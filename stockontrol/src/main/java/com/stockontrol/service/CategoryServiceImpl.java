package com.stockontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockontrol.entity.Category;
import com.stockontrol.repository.CategoryRepository;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	@Transactional
	public Category save(Category category)
	{
		return categoryRepository.save(category);
	}

}
