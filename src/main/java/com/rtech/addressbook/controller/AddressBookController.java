package com.rtech.addressbook.controller;

import com.rtech.addressbook.model.Address;
import com.rtech.addressbook.service.AddressService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rtech/v1/addresses")
public class AddressBookController {
	private AddressService addressService;

	public AddressBookController(AddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Fetch all Address")
	public List<Address> getAllAddress() {
		return this.addressService.getAllAddress();
	}

	@GetMapping("/{id}")
	@ApiOperation("Fetch Address by ID")
	@ResponseStatus(HttpStatus.OK)
	public Address getById(
			@ApiParam(value = "Address ID")
			@PathVariable(value = "id") String id) {
		return this.addressService.getAddressById(id);
	}

	@ApiOperation("Search Address by City")
	@GetMapping("/search")
	public List<Address> searchByCity(
			@ApiParam(value = "City Name")
			@RequestParam(value = "city") String city) {
		return this.addressService.getAddressByCity(city);
	}

	@ApiOperation("Update Address")
	@PutMapping("/{id}")
	public Address updateAddress(
			@ApiParam(value = "Address ID")
			@PathVariable(value = "id") String id,
			@RequestBody Address address) {
		address.setId(id);
		return this.addressService.updateAddress(address);
	}

	@ApiOperation("Delete Address by ID")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAddress(
			@ApiParam(value = "Address ID")
			@PathVariable(value = "id") String id) {
		this.addressService.deleteAddressById(id);
	}

}
