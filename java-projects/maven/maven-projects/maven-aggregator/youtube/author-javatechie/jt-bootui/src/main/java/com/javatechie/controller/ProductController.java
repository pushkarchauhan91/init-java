package com.javatechie.controller;

import com.javatechie.entity.Product;
import com.javatechie.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<List<Product>> addProducts(@RequestBody List<Product> products) {
        log.info("Received request to add {} products", products != null ? products.size() : 0);
        List<Product> savedProducts = productService.saveProducts(products);
        return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        log.info("Received request to fetch all products");
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String category) {
        log.info("Received request to fetch products by category={}", category);
        List<Product> products = productService.getProduct(category);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        log.info("Received request to update product with id={}", id);
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("Received request to delete product with id={}", id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
