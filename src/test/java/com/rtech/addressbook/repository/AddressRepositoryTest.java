package com.rtech.addressbook.repository;

import com.rtech.addressbook.model.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class AddressRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @DisplayName("Repository - Create Address")
    public void createAddress() {
        //given
        Address testAddress = new Address("isfasdf", "testCity", "testPostCode", "TestCountry", "43542353425");

        // when
        Address savedAddress = addressRepository.save(testAddress);

        //then
        then(savedAddress.getId())
                .as("Check if the Id generated for the saved Address")
                .isNotEmpty();
    }

    @Test
    @DisplayName("Repository - Find Address by Id")
    public void testFindAddressById() {

        //given
        Address testAddress = new Address("isfasdf", "testCity", "testPostCode", "TestCountry", "43542353425");

        addressRepository.save(testAddress);

        // when
        Optional<Address> fetchAddress = addressRepository.findById(testAddress.getId());

        //then
        then(testAddress.getId())
                .as("Validate the Id as saved same find.")
                .isEqualTo(fetchAddress.get().getId());
    }
}
