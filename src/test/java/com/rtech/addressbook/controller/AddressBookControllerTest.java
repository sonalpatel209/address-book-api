package com.rtech.addressbook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtech.addressbook.exception.AddressNotFound;
import com.rtech.addressbook.model.Address;
import com.rtech.addressbook.service.AddressService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AddressBookController.class)
public class AddressBookControllerTest {

    @MockBean
    private AddressService addressService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET rtech/v1/addresses/{id} Test - Success")
    public void getAddressByIdSuccess() throws Exception {
        //given

        Address testAddress = new Address("isfasdf", "testCity", "testPostCode", "TestCountry", "43542353425");
        given(addressService.getAddressById(any())).willReturn(testAddress);
        //when

        mockMvc.perform(get("/rtech/v1/addresses/{id}", "id"))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("testCity"));

    }

    @Test
    @DisplayName("GET rtech/v1/addresses/{id} Test - Fail")
    public void getAddressByIdFail() throws Exception {
        //given

        given(addressService.getAddressById(any())).willThrow(new AddressNotFound("Test"));
        //when
        mockMvc.perform(get("/rtech/v1/addresses/{id}", "id"))
                //then
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST rtech/v1/addresses/ -success")
    public void addAddressSuccess() throws Exception {
        //given

        Address testAddress = new Address("isfasdf", "testCity", "testPostCode", "TestCountry", "43542353425");
        given(addressService.addAddress(any())).willReturn(testAddress);

        //when
        mockMvc.perform(post("/rtech/v1/addresses/", "id").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testAddress)))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("testCity"));

    }

    @Test
    @DisplayName("DELETE rtech/v1/addresses/{id} Test - Fail")
    public void deleteAddressByIdFail() throws Exception {
        //given

        doThrow(new AddressNotFound("Address not found for test")).when(addressService).deleteAddressById(any());

        //when
        mockMvc.perform(delete("/rtech/v1/addresses/{id}", "id"))
                //then
                .andExpect(status().isNotFound());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
