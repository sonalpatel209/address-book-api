package com.rtech.addressbook.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Getter
@Setter
@Document("address")
public class Address {

	@Id
	private String id;

	@NonNull
	private String street;

	@Indexed
	@NonNull
	private String city;
	private String postCode;

	@NonNull
	private String country;

	private String mobile;

	public Address(String street, String city, String postCode, String country, String mobile) {
		super();
		this.street = street;
		this.city = city;
		this.postCode = postCode;
		this.country = country;
		this.mobile = mobile;
	}


}