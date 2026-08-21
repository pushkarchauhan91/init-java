package com.javatechie.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.entity.Product;
import com.javatechie.repository.ProductRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataInitializer {

    private final ProductRepository productRepository;

    public DataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean
    public ApplicationRunner loadData() {
        return args -> {
            try {
                // Check if products already exist in DB
                long productCount = productRepository.count();
                if (productCount > 0) {
                    System.out.println("✓ Database already contains " + productCount + " products. Skipping data initialization.");
                    return;
                }

                System.out.println("Loading products from products.json...");

                // Load products.json from classpath
                ClassPathResource resource = new ClassPathResource("products.json");
                InputStream inputStream = resource.getInputStream();

                // Parse JSON to Product list
                ObjectMapper objectMapper = new ObjectMapper();
                List<Product> products = objectMapper.readValue(inputStream, new TypeReference<List<Product>>() {
                });
                LocalDateTime now = LocalDateTime.now();
                products.forEach(product -> {
                    if (product.getCreatedAt() == null) {
                        product.setCreatedAt(now);
                    }
                    product.setUpdatedAt(now);
                });

                System.out.println("Loaded " + products.size() + " products from JSON file");

                // Save all products to database
                productRepository.saveAll(products);

                System.out.println("✓ Successfully loaded " + products.size() + " products into database");

            } catch (Exception e) {
                System.err.println("Error loading products data from JSON file: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}


