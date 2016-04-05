package com.stockontrol.application.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockontrol.domain.entity.Batch;
import com.stockontrol.domain.entity.Product;
import com.stockontrol.domain.service.BatchService;
import com.stockontrol.domain.service.ProductService;
import com.stockontrol.domain.util.SimplePageRequest;

@RestController
@RequestMapping("/api/products")
public class ProductsController
{
	@Autowired
	private ProductService productService;
	@Autowired
	private BatchService batchService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public Iterable<Product> index(@RequestParam(required = false, value = "name") String name, @RequestParam(name = "page", defaultValue = "0") int page)
	{
		return productService.listAllProductsByFilters(null, name, new SimplePageRequest(page, 20));
	}

	@RequestMapping(value = "/{id}/batches", method = RequestMethod.GET)
	public Iterable<Batch> listBatches(@PathVariable Long id, @RequestParam(name = "page", defaultValue = "0") int page)
	{
		return batchService.listAllByFilters(null, null, id, new SimplePageRequest(page, 20));
	}
	
	@RequestMapping(value = "/{id}/batches/expiring", method = RequestMethod.GET)
	public Iterable<Batch> listExpiringBatches(@PathVariable Long id, @RequestParam(name = "page", defaultValue = "0") int page)
	{
		return batchService.listAllExpiring(id, new SimplePageRequest(page, 20));
	}
	
	@RequestMapping(value = "/{id}/batches/expired", method = RequestMethod.GET)
	public Iterable<Batch> listExpiredBatches(@PathVariable Long id, @RequestParam(name = "page", defaultValue = "0") int page)
	{
		return batchService.listAllExpired(id, new SimplePageRequest(page, 20));
	}
	
	@RequestMapping(value = "/{id}/batches", method = RequestMethod.POST)
	public Batch insertBatch(@PathVariable Long id, @RequestBody Batch batch)
	{
		Product product = productService.findProduct(id);
		batch.setProduct(product);
		return batchService.insert(batch);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Product show(@PathVariable Long id)
	{
		return productService.findProduct(id);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.POST })
	public Product update(@PathVariable Long id, @RequestBody Product sentProduct)
	{
		Product product = productService.findProduct(id);
		sentProduct.copyNonNullpropertiesTo(product);
		return productService.updateProduct(product);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id)
	{
		productService.deleteProduct(id);
	}
}
