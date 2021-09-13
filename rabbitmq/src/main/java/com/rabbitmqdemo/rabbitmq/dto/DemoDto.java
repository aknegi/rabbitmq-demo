package com.rabbitmqdemo.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoDto {
    private String id;
    private Date createdAt;
    private String name;
    private String avatar;
    private String email;
    private String imageUrl;
}
