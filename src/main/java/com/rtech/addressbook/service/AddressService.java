package com.rtech.addressbook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rtech.addressbook.model.Address;
import com.rtech.addressbook.repository.AddressRepository;

@Service
public class AddressService {
	
	private final AddressRepository addressRepository;

	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	public void addAddress(Address address) {
		this.addressRepository.insert(address);
	}
	
	public void updateAddress(Address address) {
		this.addressRepository.findById(address.getId()).orElseThrow(()->new RuntimeException(String.format("Cannot find Address by ID %s", address.getId())));
		this.addressRepository.save(address);
	}
	
	public void deleteAddressById(Integer id) {
		this.addressRepository.deleteById(id);
	}
	
	public List<Address> getAllAddress(){
		return this.addressRepository.findAll();
	}
	
	public Optional<Address> getAddressById(Integer id){
		return this.addressRepository.findById(id);
	}
	
	public List<Address> getAddressByCity(String city){
		return this.addressRepository.findByCity(city);
	}

}
