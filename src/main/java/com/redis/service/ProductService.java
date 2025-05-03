package com.redis.service;

import com.redis.entity.Product;
import com.redis.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public Product save(Product product) {
        return productDao.save(product);
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public Product findProductById(int id) {
        return productDao.findProductById(id);
    }

    public String deleteProductById(int id) {
        return productDao.deleteProductById(id);
    }
}
