package com.rtech.addressbook.exception;

import lombok.Builder;

@Builder
public class ErrorMessage {
    String message;
    String code;
    String description;
}
