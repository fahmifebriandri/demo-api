package com.domain.demoapi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.demoapi.dto.ResponseData;
import com.domain.demoapi.dto.SearchData;
import com.domain.demoapi.models.entities.Product;
import com.domain.demoapi.models.entities.Supplier;
import com.domain.demoapi.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productServices;
    //@Autowired
    //private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors){
        ResponseData<Product> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productServices.save(product));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Product> findAll(){
        return productServices.findAll();
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable("id") Long id){
        return productServices.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Product>> update(@Valid @RequestBody Product product, Errors errors){
        ResponseData<Product> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productServices.save(product));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        productServices.removeOne(id);
    }
    
    @PostMapping("/{id}")
    public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId){
        productServices.addSupplier(supplier, productId);
    }
    
    @PostMapping("/search/name")
    public Product getProductByName(@RequestBody SearchData searchData){
        return productServices.findByProductName(searchData.getSearchKey());
    }

    @PostMapping("/search/namelike")
    public List<Product> getProductByNameLike(@RequestBody SearchData searchData){
        return productServices.findByProductNameLike(searchData.getSearchKey());
    }

    @GetMapping("/search/category/{categoryId}")
    public List<Product> getProductByCategory(@PathVariable("categoryId") Long categoryId){
        return productServices.findByCategory(categoryId);
    }

    @GetMapping("/search/supplier/{supplierId}")
    public List<Product> getProductBySupplier(@PathVariable("supplierId") Long supplierId){
        return productServices.findBySupplier(supplierId);
    }
}
