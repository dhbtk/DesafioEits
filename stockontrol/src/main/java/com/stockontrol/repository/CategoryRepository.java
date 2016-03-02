package com.stockontrol.repository;

import org.springframework.data.repository.CrudRepository;

import com.stockontrol.entity.Category;


public interface CategoryRepository extends CrudRepository<Category, Long>
{
	
}
