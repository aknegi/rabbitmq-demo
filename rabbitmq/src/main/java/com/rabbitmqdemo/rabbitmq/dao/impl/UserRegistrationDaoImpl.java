package com.rabbitmqdemo.rabbitmq.dao.impl;

import com.rabbitmqdemo.rabbitmq.dao.UserRegistrationDao;
import com.rabbitmqdemo.rabbitmq.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserRegistrationDaoImpl implements UserRegistrationDao {

    @Autowired
    EntityManager entityManager;
    @Override
    public User findByEmail(String email) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from User where email = :email ");
        query.setParameter("email", email);
        return (User) query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public String save(User user){
        Session session= entityManager.unwrap(Session.class);
        session.save(user);
        return user.getUuid();
    }
}
