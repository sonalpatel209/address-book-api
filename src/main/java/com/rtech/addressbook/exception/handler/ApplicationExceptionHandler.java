package com.rtech.addressbook.exception.handler;

import com.rtech.addressbook.exception.AddressNotFound;
import com.rtech.addressbook.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AddressNotFound.class)
    @ResponseBody
    ErrorMessage handleAddressNotFound(AddressNotFound addressNotFound) {
        return ErrorMessage
                .builder()
                .message(addressNotFound.getMessage())
                .code(HttpStatus.NOT_FOUND.name())
                .build();
    }

}
