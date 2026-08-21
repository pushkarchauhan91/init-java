package com.javatechie.controller;

import com.javatechie.entity.Product;
import com.javatechie.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ui/products")
@Slf4j
public class ProductViewController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(@RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "10") int size,
                               Model model) {
        log.info("Rendering product list UI with pagination. page={}, size={}", page, size);
        Page<Product> productPage = productService.getProductsPage(page, size);
        List<Product> products = productPage.getContent();

        model.addAttribute("products", products);
        model.addAttribute("productPage", productPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("hasPrevious", productPage.hasPrevious());
        model.addAttribute("hasNext", productPage.hasNext());
        model.addAttribute("pageTitle", "Product Catalog");
        return "products";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        log.info("Rendering create product form");
        model.addAttribute("product", new Product());
        model.addAttribute("pageTitle", "Create Product");
        return "product-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        log.info("Rendering edit form for product id={}", id);
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("pageTitle", "Edit Product");
        return "product-form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        log.info("Processing save for product id={}", product.getId());
        productService.saveProduct(product);
        return "redirect:/ui/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        log.info("Processing delete for product id={}", id);
        productService.deleteProduct(id);
        return "redirect:/ui/products";
    }
}

