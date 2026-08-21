package com.javatechie.service;

import com.javatechie.entity.Product;
import com.javatechie.exception.ProductNotFoundException;
import com.javatechie.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${product-service.kafka.producer.topic}")
    private String productTopic;

    @CacheEvict(value = "products", allEntries = true)
    public List<Product> saveProducts(List<Product> products) {
        log.info("Saving list of products. Count={}", products != null ? products.size() : 0);
        LocalDateTime now = LocalDateTime.now();
        products.forEach(product -> {
            if (product.getCreatedAt() == null) {
                product.setCreatedAt(now);
            }
            product.setUpdatedAt(now);
        });
        sendProductToKafka(products);
        return productRepository.saveAll(products);
    }

    @Cacheable(value = "products", key = "'all'", unless = "#result == null || #result.isEmpty()")
    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        return productRepository.findAll();
    }

    public Page<Product> getProductsPage(int page, int size) {
        log.info("Fetching products page. page={}, size={}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    @Cacheable(value = "products", key = "#category")
    public List<Product> getProduct(String category) {
        log.info("Fetching products for category={}", category);
        return productRepository.findByCategory(category);
    }

    @CacheEvict(value = "products", allEntries = true)
    public Product saveProduct(Product product) {
        log.info("Saving single product with id={}", product.getId());
        LocalDateTime now = LocalDateTime.now();
        if (product.getCreatedAt() == null) {
            product.setCreatedAt(now);
        }
        product.setUpdatedAt(now);
        Product saved = productRepository.save(product);
        sendProductToKafka(List.of(saved));
        log.info("Successfully saved product with id={}", saved.getId());
        return saved;
    }

    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        log.info("Fetching product by id={}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @CacheEvict(value = "products", allEntries = true)
    public Product updateProduct(Long id, Product updatedProduct) {
        log.info("Updating product with id={}", id);
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(existingProduct);
        log.info("Successfully updated product with id={}", id);
        return savedProduct;
    }

    @CacheEvict(value = "products", allEntries = true)
    public void deleteProduct(Long id) {
        log.info("Deleting product with id={}", id);
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            log.warn("Attempted to delete non-existent product with id={}", id);
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
        log.info("Successfully deleted product with id={}", id);
    }


    private void sendProductToKafka(List<Product> products) {
        for (Product product : products) {
            log.info("Sending product with id={} to Kafka topic", product.getId());
            kafkaTemplate.send(productTopic, product);
        }

    }

}