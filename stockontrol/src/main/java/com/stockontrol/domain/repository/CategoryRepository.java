package com.stockontrol.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.stockontrol.domain.entity.Category;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long>, QueryDslPredicateExecutor<Category>
{	
	public Page<Category> findAllByNameIgnoreCaseContaining(String name, Pageable page);
}
