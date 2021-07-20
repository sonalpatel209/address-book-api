package com.rtech.addressbook.config;

import com.rtech.addressbook.model.Address;
import com.rtech.addressbook.repository.AddressRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = AddressRepository.class)
@Configuration
public class MongoDBConfig {
	
	// TODO For testing only.. remove 
	@Bean
	CommandLineRunner commandLineRunner(AddressRepository addressRepository) {
		return args -> {
			addressRepository.save(new Address("2 Fernway Drive", "Melbourne", "3100", "AU", "0430001200"));
			addressRepository.save(new Address("36 Sunset Drive", "Melbourne", "3102", "AU", "0430001202"));
			addressRepository.save(new Address("54 Mountainview Blvd", "Sydney", "3103", "AU", "0430001203"));
		};
	}

}
