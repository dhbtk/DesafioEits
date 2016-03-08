package com.stockontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.stockontrol.entity.Batch;

@Repository("batchRepository")
public interface BatchRepository extends JpaRepository<Batch, Long>
{
	public List<Batch> findAllByProductId(Long productId);
	
	@Query("select b from Batch b where date(b.expiresAt) <= date(now())")
	public List<Batch> findAllExpired();
	
	@Query("select b from Batch b where date(b.expiresAt) <= date(now()) and b.productId = ?1")
	public List<Batch> findAllExpiredByProductId(Long productId);
	
	@Query("select b from Batch b where date(b.expiresAt) > date(now())")
	public List<Batch> findAllExpiring();
	
	@Query("select b from Batch b where date(b.expiresAt) > date(now()) and b.productId = ?1")
	public List<Batch> findAllExpiringByProductId(Long productId);
}
