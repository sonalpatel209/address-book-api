package com.rtech.addressbook.repository;

import com.rtech.addressbook.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface AddressRepository extends MongoRepository<Address, String> {

	@Query("{'city':?0}")
	List<Address> findByCity(String city);
}
