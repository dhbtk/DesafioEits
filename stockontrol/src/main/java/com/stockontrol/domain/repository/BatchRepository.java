package com.stockontrol.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.stockontrol.domain.entity.Batch;
import com.stockontrol.domain.entity.Product;

@Repository("batchRepository")
public interface BatchRepository extends JpaRepository<Batch, Long>, QueryDslPredicateExecutor<Batch>
{
	public Page<Batch> findAllByProduct(Product product, PageRequest page);
	
	@Query("select b from Batch b where date(b.expiresAt) <= date(now()) and b.product.id = ?1")
	public Page<Batch> listAllExpired(Long productId, PageRequest page);
	
	@Query("select b from Batch b where date(b.expiresAt) > date(now()) and b.product.id = ?1")
	public Page<Batch> listAllExpiring(Long productId, PageRequest page);
	
	// FIXME
	@Query("select b from Batch b where (?1 is null or b.product.name like '%?1%') and "
			+ "(?2 is null or b.identifier like '%?2%') and (?3 is null) and (?4 is null or b.product.id = ?4)")
	public List<Batch> listAllByFilters(String productName, String identifier, Date maxExpirationDate, Long productId);
}
