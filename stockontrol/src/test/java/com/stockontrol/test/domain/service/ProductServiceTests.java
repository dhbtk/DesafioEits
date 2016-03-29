package com.stockontrol.test.domain.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.stockontrol.domain.service.ProductService;
import com.stockontrol.test.domain.AbstractIntegrationTests;

public class ProductServiceTests extends AbstractIntegrationTests
{
	@Autowired
	private ProductService productService;
}
