package com.rabbitmqdemo.rabbitmq.controller;

import com.rabbitmqdemo.rabbitmq.dto.DemoDto;
import com.rabbitmqdemo.rabbitmq.dto.PortalResponse;
import com.rabbitmqdemo.rabbitmq.dto.UserDto;
import com.rabbitmqdemo.rabbitmq.service.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class UserController {

    @Autowired
    private UserRegistration userService;

    @PostMapping("/register")
    public PortalResponse register(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }

    @GetMapping("readData")
    public PortalResponse readData(){
        return userService.getAllData();
    }
}
