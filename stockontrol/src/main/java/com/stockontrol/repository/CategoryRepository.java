package com.stockontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockontrol.entity.Category;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long>
{
	
}
