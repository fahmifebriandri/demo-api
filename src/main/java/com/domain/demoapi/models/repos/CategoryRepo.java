package com.domain.demoapi.models.repos;

import org.springframework.data.repository.CrudRepository;

import com.domain.demoapi.models.entities.Category;

public interface CategoryRepo extends CrudRepository<Category, Long>{
    
}
