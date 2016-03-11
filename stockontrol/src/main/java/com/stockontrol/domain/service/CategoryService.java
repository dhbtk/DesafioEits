package com.stockontrol.domain.service;

import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockontrol.domain.entity.Category;
import com.stockontrol.domain.repository.CategoryRepository;

import junit.framework.Assert;

@RemoteProxy
@Service("categoryService")
public class CategoryService
{
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RemoteMethod
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public List<Category> listAllByFilters(String name)
	{
		if(name == null)
		{
			return categoryRepository.findAll();
		}
		else
		{
			return categoryRepository.findAllByNameIgnoreCaseContaining(name);
		}
	}
	
	@RemoteMethod
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public Category find(Long id)
	{
		return categoryRepository.findOne(id);
	}

	@RemoteMethod
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public Category insert(Category category)
	{
		Assert.assertNull("Categoria já existe!", category.getId());
		return categoryRepository.saveAndFlush(category);
	}
	
	@RemoteMethod
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public Category update(Category category)
	{
		Assert.assertNotNull("Categoria ainda não existe!", category.getId());
		return categoryRepository.saveAndFlush(category);
	}
	
	@RemoteMethod
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public void delete(Long id)
	{
		categoryRepository.delete(id);
	}
}
