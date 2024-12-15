package com.works.customer;

import com.works.customer.models.Comment;
import org.springframework.cloud.client.ServiceInstance;
import com.works.customer.models.ProductModel;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("customer")
public class CustomerRestController {
    final DiscoveryClient discoveryClient;
    final IProduct iProduct;
    final IPlaceHolder iPlaceHolder;
    final CircuitBreakerFactory circuitBreakerFactory;
    final CircuitBreakerFactory globalCustomConfiguration;
    public CustomerRestController(IProduct iProduct, DiscoveryClient discoveryClient, IPlaceHolder iPlaceHolder, CircuitBreakerFactory circuitBreakerFactory, CircuitBreakerFactory globalCustomConfiguration) {
        this.iProduct = iProduct;
        this.discoveryClient = discoveryClient;
        this.iPlaceHolder = iPlaceHolder;
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.globalCustomConfiguration = globalCustomConfiguration;
    }

    @GetMapping("data")
    public Map data() {
        Map map = new LinkedHashMap();
        map.put("name", "Kemal");
        map.put("surname", "Bilmem");
        map.put("email", "kemal@gmail.com");
        map.put("age", 22);
        return map;
    }
    @GetMapping("product")
    public Map product(@RequestParam(defaultValue = "0") int pageNumber) {
        Map map = new LinkedHashMap();
        ProductModel productModel = iProduct.productList(pageNumber);
        map.put("data", productModel.getContent());
        return map;
        /*RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("product");
        if (!instances.isEmpty()) {
            ServiceInstance instance = instances.get(0);
            String url = instance.getUri().toString() + "/product/list?pageNumber="+pageNumber;
            ResponseEntity<ProductModel> response = restTemplate.getForEntity(url, ProductModel.class);
            map.put("url", url);
            map.put("data", response.getBody().getContent());
        }
        return map;*/
    }

    @GetMapping("comment")
    public Comment comment(@RequestParam(defaultValue = "1") String id) {
        CircuitBreaker breaker = circuitBreakerFactory.create("breaker1");
        CircuitBreaker breaker1 = globalCustomConfiguration.create("breaker2");
        return breaker.run(
                () -> iPlaceHolder.getComments(id),
                throwable -> fallBack(id)
        );
    }
    private Comment fallBack(String id) {
        Comment comment = new Comment();
        comment.setId(0);
        comment.setComment("Sample Comment");
        comment.setPostId(1);
        comment.setUserId(1);
        return comment;
    }

}
