package com.redis.repository;

import com.redis.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

    @Autowired
    private RedisTemplate<String, Object> template;

    public static final String HASH_KEY = "Product";

    public Product save(Product product) {
        template.opsForHash().put(HASH_KEY, String.valueOf(product.getId()), product);
        return product;
    }

    public List<Product> findAll() {
        List<Object> objects = template.opsForHash().values(HASH_KEY);
        return objects.stream()
                .map(obj -> (Product) obj)
                .toList();
    }

    public Product findProductById(int id) {
        System.out.println("From DB");
        return (Product) template.opsForHash().get(HASH_KEY, String.valueOf(id));
    }

    public String deleteProductById(int id) {
        template.opsForHash().delete(HASH_KEY, String.valueOf(id));
        return "Success";
    }
}
