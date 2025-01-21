package org.kannangaranv.ecom_proj.controller;


import org.kannangaranv.ecom_proj.model.Product;
import org.kannangaranv.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String greet() {
        return "Hello World";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        System.out.println(service.getAllProducts());
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = service.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
        try {
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getProductByProductId(@PathVariable int productId) {

        Product product = service.getProductById(productId);
        byte [] imageFile = product.getImageDate();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile) {
        Product product1 = null;
        try {
            product1 = service.updateProduct(id,product,imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (product1 == null) {
            return new ResponseEntity<>("Failed to update",HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>("Successfully updated",HttpStatus.OK);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Product product = service.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>("Failed to delete",HttpStatus.NOT_FOUND);
        }else{
            service.deleteProduct(id);
            return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(String keyword) {
        List<Product> products = service.searchProduct(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}
