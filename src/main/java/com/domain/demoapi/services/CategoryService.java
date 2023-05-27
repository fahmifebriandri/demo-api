package com.domain.demoapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.demoapi.models.entities.Category;
import com.domain.demoapi.models.repos.CategoryRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public Category save(Category category){
        return categoryRepo.save(category);
    }
    public Category findOne(Long id){
        Optional<Category> category = categoryRepo.findById(id);
        if(!category.isPresent()){
            return null;
        }
        return category.get();
    }
    public Iterable<Category> findAll(){
        return categoryRepo.findAll();
    }
    public void removeOne(Long id){
        categoryRepo.deleteById(id);
    }
}
