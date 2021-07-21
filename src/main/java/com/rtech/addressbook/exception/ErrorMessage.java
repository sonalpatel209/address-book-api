package com.rtech.addressbook.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorMessage {
    String message;
    String code;
    String description;
}
