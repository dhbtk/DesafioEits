package com.stockontrol.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.stockontrol.domain.entity.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long>, QueryDslPredicateExecutor<Product>
{
}
