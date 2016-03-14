package com.stockontrol.domain.service;

import java.time.LocalDate;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockontrol.domain.entity.Batch;
import com.stockontrol.domain.entity.QBatch;
import com.stockontrol.domain.repository.BatchRepository;
import com.stockontrol.domain.util.PredicateList;

@Service("batchService")
@Transactional
@RemoteProxy(name = "batchService")
public class BatchService
{
	@Autowired
	private BatchRepository batchRepository;
	
	@PreAuthorize("hasRole('USER')")
	@RemoteMethod
	public Batch insert(Batch batch)
	{
		Assert.assertNull("Lote já existe!",batch.getId());
		return batchRepository.saveAndFlush(batch);
	}
	
	@PreAuthorize("hasRole('USER')")
	@RemoteMethod
	public Batch find(Long id)
	{
		return batchRepository.findOne(id);
	}
	
	@PreAuthorize("hasRole('USER')")
	@RemoteMethod
	public Page<Batch> listAllByFilters(String productName, String identifier, LocalDate maxExpirationDate, Long productId, PageRequest page)
	{
		QBatch batch = QBatch.batch;
		PredicateList predicates = new PredicateList();
		predicates
				.add(productName, batch.product.name.containsIgnoreCase(productName))
				.add(identifier, batch.identifier.containsIgnoreCase(identifier))
				.add(maxExpirationDate, batch.expiresAt.loe(maxExpirationDate))
				.add(productId, batch.product.id.eq(productId));
		return batchRepository.findAll(predicates.getIntersection(), page);
	}
	
	@PreAuthorize("hasRole('USER')")
	@RemoteMethod
	public Page<Batch> listAllExpired(Long productId, PageRequest page)
	{
		return batchRepository.listAllExpired(productId, page);
	}
	
	@PreAuthorize("hasRole('USER')")
	@RemoteMethod
	public Page<Batch> listAllExpiring(Long productId, PageRequest page)
	{
		return batchRepository.listAllExpiring(productId, page);
	}
	
	@PreAuthorize("hasRole('USER')")
	@RemoteMethod
	public Batch registerOutgoingById(Long id, int quantity)
	{
		Batch batch = find(id);
		Assert.assertNotNull(String.format("Lote com id %d não existe", id), batch);
		Assert.assertTrue(String.format("Não é possível remover %d itens do lote id %d com %d itens", quantity, batch.getId(), batch.getQuantity()),batch.getQuantity() >= quantity);
		if(quantity == batch.getQuantity())
		{
			batchRepository.delete(batch);
			return null;
		}
		else
		{
			batch.setQuantity(batch.getQuantity() - quantity);
			batch = batchRepository.saveAndFlush(batch);
		}
		return batch;
	}
}
