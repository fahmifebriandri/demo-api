package com.domain.demoapi.models.repos;

import org.springframework.data.repository.CrudRepository;

import com.domain.demoapi.models.entities.Supplier;

public interface SupplierRepo extends CrudRepository<Supplier, Long>{
    
}
