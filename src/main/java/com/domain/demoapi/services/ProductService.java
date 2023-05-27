package com.domain.demoapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.demoapi.models.entities.Product;
import com.domain.demoapi.models.entities.Supplier;
import com.domain.demoapi.models.repos.ProductRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SupplierService supplierService;

    public Product save(Product product){
        return productRepo.save(product);
    }
    public Product findOne(Long id){
        Optional<Product> product = productRepo.findById(id);
        if(!product.isPresent()){
            return null;
        }
        return product.get();
    }
    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }
    public void removeOne(Long id){
        productRepo.deleteById(id);
    }
    public List<Product> findByName(String name){
        return productRepo.findByNameContains(name);
    }
    public void addSupplier(Supplier suppplier, Long productID){
        Product product = findOne(productID);
        if(product == null){
            throw new RuntimeException("Product with ID "+productID+" not found"); 
        }
        product.getSuppliers().add(suppplier);
        save(product);
    }

    public Product findByProductName(String name){
        return productRepo.findProductByName(name);
    }

    public List<Product> findByProductNameLike(String name){
        return productRepo.findProductByNameLike("%"+name+"%");
    }

    public List<Product> findByCategory(Long categoryId){
        return productRepo.findProductByCategory(categoryId);
    }

    public List<Product> findBySupplier(Long supplierId){
        Supplier supplier = supplierService.findOne(supplierId);
        if(supplier == null){
            return new ArrayList<>();
        }
        return productRepo.findProductBySupplier(supplier);
    }
}
