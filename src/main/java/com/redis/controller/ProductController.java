package com.redis.controller;

import com.redis.entity.Product;
import com.redis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "Product", unless = "#result != null and #result.price < 1000")
    public Product getProductById(@PathVariable int id) {
        return productService.findProductById(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", value = "Product")
    public String deleteProductById(@PathVariable int id) {
        return productService.deleteProductById(id);
    }
}
