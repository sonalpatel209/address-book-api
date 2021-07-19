package com.rtech.addressbook.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rtech.addressbook.model.Address;
import com.rtech.addressbook.service.AddressService;

@RestController
@RequestMapping("/rtech/v1/addresses")
public class AddressBookController {
	private AddressService addressService;

	public AddressBookController(AddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping("/")
	public ResponseEntity<List<Address>> getAllAddress() {
		return ResponseEntity.ok(this.addressService.getAllAddress());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Address>> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.addressService.getAddressById(id));
	}

	@GetMapping("/city/{city}")
	public ResponseEntity<List<Address>> getByCity(@PathVariable String city) {
		return ResponseEntity.ok(this.addressService.getAddressByCity(city));
	}

	@PutMapping()
	public ResponseEntity<Void> updateAddress(@RequestBody Address address) {
		this.addressService.updateAddress(address);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAddress(@PathVariable Integer id) {
		this.addressService.deleteAddressById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
