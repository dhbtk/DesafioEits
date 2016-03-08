package com.stockontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockontrol.entity.Product;
import com.stockontrol.repository.ProductRepository;

@Service("productService")
public class ProductService
{
	@Autowired
	private ProductRepository productRepository;
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public List<Product> findAllByCategoryId(Long id)
	{
		return productRepository.findAllByCategoryId(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional
	public void delete(Product product)
	{
		productRepository.delete(product);
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public List<Product> findAll()
	{
		return productRepository.findAll();
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public Product find(Long id)
	{
		return productRepository.findOne(id);
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public Product save(Product product)
	{
		return productRepository.saveAndFlush(product);
	}
}
