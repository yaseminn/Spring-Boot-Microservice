package com.works.product.restcontrollers;

import com.works.product.entities.Address;
import com.works.product.projections.AddressJoinCity;
import com.works.product.services.AddressService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("product/address")
public class AddressRestController {
    final AddressService addressService;
    public AddressRestController(AddressService addressService) {
        this.addressService = addressService;
    }
    @PostMapping("save")
    public Address save(@RequestBody Address address) {
        return addressService.save(address);
    }
    @GetMapping("list")
    public List<AddressJoinCity> list() {
        return addressService.findAll();
    }
}