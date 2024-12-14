package com.works.customer;

import com.works.customer.models.ProductModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient("product")
public interface IProduct {
    @GetMapping("/product/list")
    ProductModel productList(@RequestParam Integer pageNumber);
}
