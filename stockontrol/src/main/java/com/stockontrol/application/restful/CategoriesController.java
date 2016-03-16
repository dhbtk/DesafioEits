package com.stockontrol.application.restful;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockontrol.domain.entity.Category;
import com.stockontrol.domain.entity.Product;
import com.stockontrol.domain.service.ProductService;

@RestController
@RequestMapping("/categories")
public class CategoriesController
{
	@Autowired
	private ProductService productService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public Iterable<Category> index(@RequestParam(required = false, value = "name") String name,
			@RequestParam(name = "page", defaultValue = "0") int page)
	{
		return productService.listAllCategoriesByFilters(name, new PageRequest(page, 20));
	}

	@RequestMapping(value = "/{id}/products", method = RequestMethod.GET)
	public Iterable<Product> listProducts(@PathVariable Long id,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "page", defaultValue = "0") int page)
	{
		return productService.listAllProductsByFilters(id, name, new PageRequest(page, 20));
	}

	@RequestMapping(value = "/{id}/products", method = RequestMethod.POST)
	public Product insertProduct(@PathVariable Long id, @Valid @RequestBody Product product)
	{
		product.setCategory(productService.findCategory(id));
		return productService.insertProduct(product);
	}

	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public Category create(@Valid @RequestBody Category category)
	{
		return productService.insertCategory(category);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Category show(@PathVariable Long id)
	{
		return productService.findCategory(id);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.POST })
	public Category update(@PathVariable Long id, @RequestBody Category sentCategory)
	{
		Category category = productService.findCategory(id);
		if (sentCategory.getName() != null)
		{
			category.setName(sentCategory.getName());
		}
		if (sentCategory.getDescription() != null)
		{
			category.setDescription(sentCategory.getDescription());
		}
		return productService.updateCategory(category);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id)
	{
		productService.deleteCategory(id);
	}
}