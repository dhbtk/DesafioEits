package com.stockontrol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stockontrol.entity.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long>
{
	@Query("select p from Product p where (:category = null or p.category.id = :category) and (:name = null or p.name like %:name%)")
	public List<Product> listAllByFilters(@Param("category") Long CategoryId, @Param("name") String name);
	
	public List<Product> findAllByCategoryId(Long categoryId);
	
	public List<Product> findAllByNameContaining(String name);
	
	public List<Product> findAllByCategoryIdAndNameContaining(Long categoryId, String name);
}
