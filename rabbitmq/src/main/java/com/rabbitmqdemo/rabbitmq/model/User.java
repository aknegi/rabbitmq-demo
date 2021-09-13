package com.rabbitmqdemo.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String city;
    private String state;
    private String country;
    @CreationTimestamp
    private Date createdDate;
    @LastModifiedDate
    private Date lastUpdated;
}
