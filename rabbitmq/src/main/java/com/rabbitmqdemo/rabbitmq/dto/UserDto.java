package com.rabbitmqdemo.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String uuid;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String city;
    private String state;
    private String country;
}
