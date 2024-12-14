package com.works.product.services;

import com.works.product.entities.Product;
import com.works.product.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        productRepository.save(product);
        return product;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> saveAll(List<Product> productList) {
        return productRepository.saveAll(productList);
    }


}
