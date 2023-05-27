package com.domain.demoapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.demoapi.models.entities.Supplier;
import com.domain.demoapi.models.repos.SupplierRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplierService {
    @Autowired
    private SupplierRepo supplierRepo;

    public Supplier save(Supplier category){
        return supplierRepo.save(category);
    }
    public Supplier findOne(Long id){
        Optional<Supplier> category = supplierRepo.findById(id);
        if(!category.isPresent()){
            return null;
        }
        return category.get();
    }
    public Iterable<Supplier> findAll(){
        return supplierRepo.findAll();
    }
    public void removeOne(Long id){
        supplierRepo.deleteById(id);
    }
}
