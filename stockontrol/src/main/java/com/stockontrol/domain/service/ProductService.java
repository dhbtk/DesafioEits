package com.stockontrol.domain.service;

import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockontrol.domain.entity.Category;
import com.stockontrol.domain.entity.Product;
import com.stockontrol.domain.repository.CategoryRepository;
import com.stockontrol.domain.repository.ProductRepository;

import org.junit.Assert;

@Service("productService")
@Transactional
@RemoteProxy(name = "productService")
public class ProductService
{
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@PreAuthorize("hasRole('USER')")
	@RemoteMethod
	public List<Product> listAllProductsByFilters(Long categoryId, String name)
	{
		if(categoryId == null && name == null)
		{
			return productRepository.findAll();
		}
		else if(categoryId == null)
		{
			return productRepository.findAllByNameContaining(name);
		}
		else if(name == null)
		{
			return productRepository.findAllByCategoryId(categoryId);
		}
		else
		{
			return productRepository.findAllByCategoryIdAndNameContaining(categoryId, name);
		}
		//return productRepository.listAllByFilters(categoryId, name);
	}
	
	@PreAuthorize("hasRole('USER')")
	@RemoteMethod
	public void deleteProduct(Long id)
	{
		productRepository.delete(id);
	}
	
	@PreAuthorize("hasRole('USER')")
	@RemoteMethod
	public List<Product> findAllProducts()
	{
		return productRepository.findAll();
	}
	
	@PreAuthorize("hasRole('USER')")
	@RemoteMethod
	public Product findProduct(Long id)
	{
		return productRepository.findOne(id);
	}
	
	@PreAuthorize("hasRole('USER')")
	@RemoteMethod
	public Product insertProduct(Product product)
	{
		Assert.assertNull("Produto já existe!", product.getId());
		return productRepository.saveAndFlush(product);
	}
	
	@PreAuthorize("hasRole('USER')")
	@RemoteMethod
	public Product updateProduct(Product product)
	{
		Assert.assertNotNull("Produto não existe!", product.getId());
		return productRepository.saveAndFlush(product);
	}
	
	@RemoteMethod
	@PreAuthorize("hasRole('USER')")
	public List<Category> listAllCategoriesByFilters(String name)
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
	public Category findCategory(Long id)
	{
		return categoryRepository.findOne(id);
	}

	@RemoteMethod
	@PreAuthorize("hasRole('USER')")
	public Category insertCategory(Category category)
	{
		Assert.assertNull("Categoria já existe!", category.getId());
		return categoryRepository.saveAndFlush(category);
	}
	
	@RemoteMethod
	@PreAuthorize("hasRole('USER')")
	public Category updateCategory(Category category)
	{
		Assert.assertNotNull("Categoria ainda não existe!", category.getId());
		return categoryRepository.saveAndFlush(category);
	}
	
	@RemoteMethod
	@PreAuthorize("hasRole('USER')")
	public void deleteCategory(Long id)
	{
		categoryRepository.delete(id);
	}
}
