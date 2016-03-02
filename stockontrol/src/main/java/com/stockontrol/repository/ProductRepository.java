package com.stockontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockontrol.entity.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long>
{

}
