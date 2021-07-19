package com.rtech.addressbook.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Document("address")
public class Address {

	@Id
	private Integer id;

	private String street;
	private String city;
	private String postCode;
	private String country;
	private String mobile;
	
	public Address(Integer id, String street, String city, String postCode, String country, String mobile) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
		this.postCode = postCode;
		this.country = country;
		this.mobile = mobile;
	}
 
	
}