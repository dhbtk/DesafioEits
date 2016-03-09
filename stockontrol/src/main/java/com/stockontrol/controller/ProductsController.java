package com.stockontrol.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockontrol.entity.Batch;
import com.stockontrol.entity.Category;
import com.stockontrol.entity.Product;
import com.stockontrol.service.CategoryService;
import com.stockontrol.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductsController
{
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public List<Product> index(@RequestParam(required = false, value = "name") String name)
	{
		return productService.listAllByFilters(null, name);
	}

	@RequestMapping(value = "/{id}/batches", method = RequestMethod.GET)
	public List<Batch> listBatches(@PathVariable Long id)
	{
		return null;
	}

	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public Product create(@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "price", required = true) BigDecimal price,
			@RequestParam(name = "category_id", required = true) Long categoryId)
	{
		Product product = new Product();
		product.setCategory(categoryService.find(categoryId));
		product.setPrice(price);
		product.setName(name);
		return productService.insert(product);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Product show(@PathVariable Long id)
	{
		return productService.find(id);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.POST })
	public Product update(@PathVariable Long id, @RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "price", required = false) BigDecimal price)
	{
		Product product = productService.find(id);
		if(name != null)
		{
			product.setName(name);
		}
		if(price != null)
		{
			product.setPrice(price);
		}
		return productService.update(product);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id)
	{
		productService.delete(id);
	}
}
