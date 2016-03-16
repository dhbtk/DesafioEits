package com.stockontrol.domain.service;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockontrol.domain.entity.Category;
import com.stockontrol.domain.entity.Product;
import com.stockontrol.domain.entity.QCategory;
import com.stockontrol.domain.entity.QProduct;
import com.stockontrol.domain.repository.CategoryRepository;
import com.stockontrol.domain.repository.ProductRepository;
import com.stockontrol.domain.util.PredicateList;

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
	public Page<Product> listAllProductsByFilters(Long categoryId, String name, PageRequest page)
	{
		QProduct product = QProduct.product;
		PredicateList predicates = new PredicateList();
		predicates.add(categoryId, () -> product.category.id.eq(categoryId)).add(name,
				() -> product.name.containsIgnoreCase(name));
		return productRepository.findAll(predicates.getIntersection(), page);
	}

	@PreAuthorize("hasRole('USER')")
	@RemoteMethod
	public void deleteProduct(Long id)
	{
		productRepository.delete(id);
	}

	@PreAuthorize("hasRole('USER')")
	@RemoteMethod
	public Page<Product> findAllProducts(PageRequest page)
	{
		return productRepository.findAll(page);
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
	public Page<Category> listAllCategoriesByFilters(String name, PageRequest page)
	{
		QCategory category = QCategory.category;
		if (name == null)
		{
			return categoryRepository.findAll(page);
		} else
		{
			return categoryRepository.findAll(category.name.containsIgnoreCase(name), page);
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
