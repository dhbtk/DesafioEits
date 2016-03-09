package com.stockontrol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stockontrol.entity.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long>
{
	@Query("select p from Product p where (?1 = null or p.category.id = ?1) and (?2 = null or p.name like '%?2')")
	public List<Product> listAllByFilters(Long Categoryid, String name);
}
