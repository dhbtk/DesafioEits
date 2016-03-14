package com.stockontrol.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.stockontrol.domain.entity.Batch;
import com.stockontrol.domain.entity.Product;

@Repository("batchRepository")
public interface BatchRepository extends JpaRepository<Batch, Long>, QueryDslPredicateExecutor<Batch>
{
	public Page<Batch> findAllByProduct(Product product, Pageable page);
	
	@Query("select b from Batch b where date(b.expiresAt) <= date(now()) and b.product.id = ?1")
	public Page<Batch> listAllExpired(Long productId, Pageable page);
	
	@Query("select b from Batch b where date(b.expiresAt) > date(now()) and b.product.id = ?1")
	public Page<Batch> listAllExpiring(Long productId, Pageable page);
}
