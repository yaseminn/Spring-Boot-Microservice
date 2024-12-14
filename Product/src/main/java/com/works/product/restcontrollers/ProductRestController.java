package com.works.product.restcontrollers;

import com.works.product.entities.Product;
import com.works.product.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductRestController {

    final ProductService productService;
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("save")
    public Product save(@Valid @RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("list")
    public List<Product> list() {
        return productService.findAll();
    }

    @PostMapping("saveAll")
    public List<Product> saveAll(@Valid @RequestBody List<Product> products) {
        return productService.saveAll(products);
    }


}