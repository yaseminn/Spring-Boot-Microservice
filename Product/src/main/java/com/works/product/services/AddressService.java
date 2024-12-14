package com.works.product.services;

import com.works.product.entities.Address;
import com.works.product.projections.AddressJoinCity;
import com.works.product.repositories.AddressRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AddressService {
    final AddressRepository addressRepository;
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    public Address save(Address address) {
        return addressRepository.save(address);
    }
    public List<AddressJoinCity> findAll(int cid) {
        return addressRepository.joinAddress(cid);
    }
}