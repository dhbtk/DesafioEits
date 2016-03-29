package com.stockontrol.domain.repository;

import java.time.LocalDateTime;

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
	
	@Query("select b from Batch b where date(b.expiresAt) <= date(?2) and b.product.id = ?1")
	public Page<Batch> listAllExpiringBefore(Long productId, LocalDateTime date, Pageable page);
	
	@Query("select b from Batch b where date(b.expiresAt) > date(?2) and b.product.id = ?1")
	public Page<Batch> listAllExpiringAfter(Long productId, LocalDateTime date, Pageable page);
}
