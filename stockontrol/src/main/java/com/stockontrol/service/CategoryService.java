package com.stockontrol.service;

import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockontrol.entity.Category;
import com.stockontrol.repository.CategoryRepository;

@RemoteProxy
@Service("categoryService")
public class CategoryService
{
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RemoteMethod
	@Transactional
	public List<Category> findAll()
	{
		return categoryRepository.findAll();
	}
	
	@RemoteMethod
	@Transactional
	public Category find(Long id)
	{
		return categoryRepository.findOne(id);
	}

	@RemoteMethod
	@Transactional
	public Category save(Category category)
	{
		return categoryRepository.saveAndFlush(category);
	}
	
	@RemoteMethod
	@Transactional
	public void delete(Category category)
	{
		categoryRepository.delete(category);
	}
}
