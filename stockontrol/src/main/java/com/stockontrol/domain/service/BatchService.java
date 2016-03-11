package com.stockontrol.domain.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockontrol.domain.entity.Batch;
import com.stockontrol.domain.repository.BatchRepository;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.junit.Assert;

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
	public List<Batch> listAllByFilters(String productName, String identifier, Date maxExpirationDate, Long productId)
	{
		return batchRepository.listAllByFilters(productName, identifier, maxExpirationDate, productId);
	}
	
	@PreAuthorize("hasRole('USER')")
	@RemoteMethod
	public List<Batch> listAllExpired(Long productId)
	{
		return batchRepository.listAllExpired(productId);
	}
	
	@PreAuthorize("hasRole('USER')")
	@RemoteMethod
	public List<Batch> listAllExpiring(Long productId)
	{
		return batchRepository.listAllExpiring(productId);
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
