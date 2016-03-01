package com.stockontrol.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.stockontrol.entity.Category;

@Repository("categoryRepository")
public class CategoryRepositoryImpl implements CategoryRepository
{
	@PersistenceContext
	private EntityManager em;
	
	public Category save(Category category)
	{
		em.persist(category);
		em.flush();
		
		return category;
	}
}
