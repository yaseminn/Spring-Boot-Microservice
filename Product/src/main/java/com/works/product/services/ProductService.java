package com.works.product.services;

import com.works.product.entities.Product;
import com.works.product.repositories.ProductRepository;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Service
public class ProductService {

    final ProductRepository productRepository;
    final CacheManager cacheManager;
    final JmsTemplate jmsTemplate;

    public ProductService(JmsTemplate jmsTemplate, ProductRepository productRepository, CacheManager cacheManager) {
        this.jmsTemplate = jmsTemplate;
        this.productRepository = productRepository;
        this.cacheManager = cacheManager;
    }

    public Product save(Product product) {
        jmsTemplate.convertAndSend("q1", product);
        return product;
    }
    // JMS Listener
    @JmsListener(containerFactory = "productContainer", destination = "q1")
    public void productListener( Product product ) {
        productRepository.save(product);
        cacheManager.getCache("product").clear();
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
