package com.stockontrol.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockontrol.entity.Batch;
import com.stockontrol.entity.Product;
import com.stockontrol.repository.BatchRepository;

@Service("batchService")
public class BatchService
{
	@Autowired
	private BatchRepository batchRepository;
	@Autowired
	private ProductService productService;
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public Batch create(Batch batch)
	{
		return batchRepository.saveAndFlush(batch);
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public Batch find(Long id)
	{
		return batchRepository.findOne(id);
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public List<Batch> findAll()
	{
		return batchRepository.findAll();
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public List<Batch> findAllByProductId(Long productId)
	{
		return batchRepository.findAllByProduct(productService.find(productId));
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public List<Batch> findAllExpired()
	{
		return batchRepository.findAllExpired();
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public List<Batch> findAllExpiredByProductId(Long productId)
	{
		return batchRepository.findAllExpiredByProduct(productService.find(productId));
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public List<Batch> findAllExpiring()
	{
		return batchRepository.findAllExpiring();
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public List<Batch> findAllExpiringByProductId(Long productId)
	{
		return batchRepository.findAllExpiringByProduct(productService.find(productId));
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public Batch registerOutgoingById(Long id, int quantity)
	{
		Batch batch = find(id);
		if(batch == null)
		{
			throw new EntityNotFoundException(String.format("Lote com id %d não encontrado",id));
		}
		if(quantity > batch.getQuantity())
		{
			throw new IllegalArgumentException("Tentando remover mais itens do lote do que há no lote");
		}
		else if(quantity == batch.getQuantity())
		{
			batchRepository.delete(batch);
			return null;
		}
		return batch;
	}
}
