package com.rtech.addressbook.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.rtech.addressbook.model.Address;


public interface AddressRepository extends MongoRepository<Address, Integer>{

	@Query("{'city':?0}")
	List<Address> findByCity(String city);
}
