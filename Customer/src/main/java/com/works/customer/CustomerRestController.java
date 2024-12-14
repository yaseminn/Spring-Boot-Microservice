package com.works.customer;

import com.works.customer.models.Comment;
import org.springframework.cloud.client.ServiceInstance;
import com.works.customer.models.ProductModel;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
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
    public CustomerRestController(DiscoveryClient discoveryClient, IProduct iProduct, IPlaceHolder iPlaceHolder) {
        this.discoveryClient = discoveryClient;
        this.iProduct = iProduct;
        this.iPlaceHolder = iPlaceHolder;
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
        return iPlaceHolder.getComments(id);
    }
}
