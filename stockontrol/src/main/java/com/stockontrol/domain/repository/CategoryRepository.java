package com.stockontrol.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockontrol.domain.entity.Category;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long>
{	
	public List<Category> findAllByNameIgnoreCaseContaining(String name);
}
