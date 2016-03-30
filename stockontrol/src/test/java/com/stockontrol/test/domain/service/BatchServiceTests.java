package com.stockontrol.test.domain.service;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.stockontrol.domain.entity.Batch;
import com.stockontrol.domain.service.BatchService;
import com.stockontrol.domain.service.ProductService;
import com.stockontrol.test.domain.AbstractIntegrationTests;

@DatabaseSetup({ "/sampleUsers.xml", "/sampleCategories.xml", "/sampleProducts.xml", "/sampleBatches.xml" })
public class BatchServiceTests extends AbstractIntegrationTests
{
	@Autowired
	private BatchService batchService;
	@Autowired
	private ProductService productService;
	@Autowired
	private Clock clock;

	@Test
	public void shouldReturnFixedTime()
	{
		Clock clock = Clock.fixed(Instant.parse("2016-03-30T15:00:00.00Z"), ZoneId.of("America/Sao_Paulo"));
		assertTrue(clock.instant().equals(this.clock.instant()));
	}

	@Test
	public void shouldInsertBatch()
	{
		Batch b = new Batch();
		b.setIdentifier("ABCD001");
		b.setProduct(productService.findProduct(new Long(1)));
		b.setQuantity(new Long(50));
		b.setManufacturedAt(LocalDate.now(clock));
		b.setExpiresAt(LocalDate.now(Clock.offset(clock, Duration.ofDays(60))));
		b = batchService.insert(b);
		assertNotNull(b.getId());
	}

	@Test
	public void shouldFindBatch()
	{
		assertNotNull(batchService.find(new Long(1)));
	}

	@Test
	public void shouldListAllByFilters()
	{
		Page<Batch> res;

		// Nome do produto
		res = batchService.listAllByFilters("Fandangos", null, null, null, null);
		assertTrue(res.getContent().size() == 14);

		// Identificador
		// No caso desse conjunto de dados o prefixo de 4 letras do id é baseado
		// no nome do produto, aí fica fácil para determinar quantos devem sair
		res = batchService.listAllByFilters(null, "E778", null, null, null);
		assertTrue(res.getContent().size() == 14);
		
		// Id do produto
		res = batchService.listAllByFilters(null, null, null, new Long(1), null);
		assertTrue(res.getContent().size() == 14);
		
		// Data máxima de vencimento
		res = batchService.listAllByFilters(null, null, LocalDate.now(clock), null, null);
		assertTrue(res.getContent().size() == 81);
	}
	
	@Test
	public void shouldListAllExpired()
	{
		Page<Batch> res;
		// Sem produto
		res = batchService.listAllExpired(null, null);
		assertTrue(res.getContent().size() == 81);
		// Com
		res = batchService.listAllExpired(new Long(1), null);
		assertTrue(res.getContent().size() == 7);
	}
	
	@Test
	public void shouldListAllExpiring()
	{
		Page<Batch> res;
		// Sem produto
		res = batchService.listAllExpiring(null, null);
		assertTrue(res.getContent().size() == 155);
		// Com
		res = batchService.listAllExpiring(new Long(1), null);
		assertTrue(res.getContent().size() == 7);
	}
	
	@Test
	public void shouldRegisterOutgoingAndDeleteItself()
	{
		Batch b = batchService.registerOutgoingById(new Long(1), 99);
		assertTrue(b == null);
	}
	
	@Test
	public void shouldRegisterOutgoing()
	{
		Batch b = batchService.registerOutgoingById(new Long(1), 98);
		assertNotNull(b);
	}
	
	@Test(expected = AssertionError.class)
	public void shouldNotRegisterOutgoing()
	{
		batchService.registerOutgoingById(new Long(1), 100);
	}
}
