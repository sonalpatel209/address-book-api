package com.rtech.addressbook.service;

import com.rtech.addressbook.exception.AddressNotFound;
import com.rtech.addressbook.model.Address;
import com.rtech.addressbook.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

	private final AddressRepository addressRepository;

	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	public Address addAddress(Address address) {
		return this.addressRepository.insert(address);
	}

	public Address updateAddress(Address address) {
		this.addressRepository.findById(address.getId())
				.orElseThrow(() -> new RuntimeException(String.format("Cannot"
						+ " find Address by ID %s", address.getId())));
		return this.addressRepository.save(address);
	}

	public void deleteAddressById(
			String id) {
		this.addressRepository.findById(id)
				.orElseThrow(() -> new AddressNotFound(String.format("Cannot"
						+ " find Address by ID %s", id)));
		this.addressRepository.deleteById(id);
	}

	public List<Address> getAllAddress() {
		return this.addressRepository.findAll();
	}

	public Address getAddressById(String id) {
		return this.addressRepository.findById(id)
				.orElseThrow(() -> new AddressNotFound(String.format("Cannot"
						+ " find Address by ID %s", id)));
	}

	public List<Address> getAddressByCity(String city) {
		return this.addressRepository.findByCity(city);
	}
}
