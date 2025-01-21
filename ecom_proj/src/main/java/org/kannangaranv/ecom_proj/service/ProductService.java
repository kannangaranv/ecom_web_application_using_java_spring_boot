package org.kannangaranv.ecom_proj.service;

import org.kannangaranv.ecom_proj.model.Product;
import org.kannangaranv.ecom_proj.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        System.out.println(repo.findAll());
        return repo.findAll();
    }
}
