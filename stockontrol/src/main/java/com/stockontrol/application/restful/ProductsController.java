package com.stockontrol.application.restful;

import java.util.List;

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

@RestController
@RequestMapping("/products")
public class ProductsController
{
	@Autowired
	private ProductService productService;
	@Autowired
	private BatchService batchService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public List<Product> index(@RequestParam(required = false, value = "name") String name)
	{
		return productService.listAllByFilters(null, name);
	}

	@RequestMapping(value = "/{id}/batches", method = RequestMethod.GET)
	public List<Batch> listBatches(@PathVariable Long id)
	{
		return batchService.listAllByFilters(null, null, null, id);
	}
	
	@RequestMapping(value = "/{id}/batches/expiring", method = RequestMethod.GET)
	public List<Batch> listExpiringBatches(@PathVariable Long id)
	{
		return batchService.listAllExpiring(id);
	}
	
	@RequestMapping(value = "/{id}/batches/expired", method = RequestMethod.GET)
	public List<Batch> listExpiredBatches(@PathVariable Long id)
	{
		return batchService.listAllExpired(id);
	}
	
	@RequestMapping(value = "/{id}/batches", method = RequestMethod.POST)
	public Batch insertBatch(@PathVariable Long id, @RequestBody Batch batch)
	{
		Product product = productService.find(id);
		batch.setProduct(product);
		return batchService.insert(batch);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Product show(@PathVariable Long id)
	{
		return productService.find(id);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.POST })
	public Product update(@PathVariable Long id, @RequestBody Product sentProduct)
	{
		Product product = productService.find(id);
		if(sentProduct.getName() != null)
		{
			product.setName(sentProduct.getName());
		}
		if(sentProduct.getPrice() != null)
		{
			product.setPrice(sentProduct.getPrice());
		}
		if(sentProduct.getCategoryId() != null)
		{
			product.setCategory(productService.findCategory(sentProduct.getCategoryId()));
		}
		return productService.update(product);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id)
	{
		productService.delete(id);
	}
}
