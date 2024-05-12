package com.supaki.marketplace.data.request;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String email;
    private String firstName;
    private String lastName;

}
