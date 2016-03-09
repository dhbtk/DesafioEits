package com.stockontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.stockontrol.entity.Batch;
import com.stockontrol.entity.Product;

@Repository("batchRepository")
public interface BatchRepository extends JpaRepository<Batch, Long>
{
	public List<Batch> findAllByProduct(Product product);
	
	@Query("select b from Batch b where date(b.expiresAt) <= date(now()) and b.product.id = ?1")
	public List<Batch> listAllExpired(Long productId);
	
	@Query("select b from Batch b where date(b.expiresAt) > date(now()) and b.product.id = ?1")
	public List<Batch> listAllExpiring(Long productId);
	
	// FIXME
	@Query("select b from Batch b where (?1 is null or b.product.name like '%?1%') and "
			+ "(?2 is null or b.identifier like '%?2%') and (?3 is null) and (?4 is null or b.product.id = ?4)")
	public List<Batch> listAllByFilters(String productName, String identifier, Date maxExpirationDate, Long productId);
}
