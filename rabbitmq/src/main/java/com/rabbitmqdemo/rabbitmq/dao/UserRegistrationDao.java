package com.rabbitmqdemo.rabbitmq.dao;

import com.rabbitmqdemo.rabbitmq.model.User;

public interface UserRegistrationDao extends UnivDao<User, Integer> {
    public User findByEmail(String email);
    public String save(User user);

}
