package com.works.product.services;

import com.works.product.entities.Product;
import com.works.product.repositories.ProductRepository;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Service
public class ProductService {

    final ProductRepository productRepository;
    final CacheManager cacheManager;
    public ProductService(ProductRepository productRepository, CacheManager cacheManager) {
        this.productRepository = productRepository;
        this.cacheManager = cacheManager;
    }

    public Product save(Product product) {
        productRepository.save(product);
        cacheManager.getCache("product").clear();
        return product;
    }

    @Cacheable("product")
    public Page<Product> findAll(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        return productRepository.findAll(pageable);
    }

    public List<Product> saveAll(List<Product> productList) {
        return productRepository.saveAll(productList);
    }

    public Page<Product> search(String q, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        Page<Product> productList = productRepository.findByTitleContainsOrDescriptionContainsAllIgnoreCase(q,q, pageable);
        return productList;
    }


}
