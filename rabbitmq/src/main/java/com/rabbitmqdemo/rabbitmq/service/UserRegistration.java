package com.rabbitmqdemo.rabbitmq.service;

import com.rabbitmqdemo.rabbitmq.dto.DemoDto;
import com.rabbitmqdemo.rabbitmq.dto.PortalResponse;
import com.rabbitmqdemo.rabbitmq.dto.UserDto;

import java.util.List;

public interface UserRegistration {
    public PortalResponse register(UserDto userDto);

    PortalResponse getAllData();
}
