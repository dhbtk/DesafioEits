package com.stockontrol.application.restful.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockontrol.domain.entity.Batch;
import com.stockontrol.domain.service.BatchService;
import com.stockontrol.domain.util.SimplePageRequest;

@RestController
@RequestMapping("/api/batches")
public class BatchesController
{
	@Autowired
	private BatchService batchService;

	@RequestMapping(path = {"/", ""}, method = RequestMethod.GET)
	public Page<Batch> index(@RequestParam(name = "filter", required = false) String filter,
			@RequestParam(name = "maxDate", required = false) LocalDate date,
			@RequestParam(name = "productId", required = false) Long productId,
			@RequestParam(name = "page", defaultValue = "0") int page)
	{
		return batchService.listAllByFilters(filter, date, productId, new SimplePageRequest(page, 20));
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Batch show(@PathVariable Long id)
	{
		return batchService.find(id);
	}
	
	@RequestMapping(path = "/{id}/outgoing/{quantity}", method = RequestMethod.POST)
	public Batch outgoing(@PathVariable Long id, @PathVariable int quantity)
	{
		return batchService.registerOutgoingById(id, quantity);
	}
}
