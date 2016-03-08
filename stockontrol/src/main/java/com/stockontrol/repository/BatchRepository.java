package com.stockontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.stockontrol.entity.Batch;
import com.stockontrol.entity.Product;

@Repository("batchRepository")
public interface BatchRepository extends JpaRepository<Batch, Long>
{
	public List<Batch> findAllByProduct(Product product);
	
	@Query("select b from Batch b where date(b.expiresAt) <= date(now())")
	public List<Batch> findAllExpired();
	
	@Query("select b from Batch b where date(b.expiresAt) <= date(now()) and b.product = ?1")
	public List<Batch> findAllExpiredByProduct(Product product);
	
	@Query("select b from Batch b where date(b.expiresAt) > date(now())")
	public List<Batch> findAllExpiring();
	
	@Query("select b from Batch b where date(b.expiresAt) > date(now()) and b.product = ?1")
	public List<Batch> findAllExpiringByProduct(Product product);
}
