package com.rabbitmqdemo.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortalResponse {
    private String status;
    private String message;
    private Object data;
    public PortalResponse(String status, String message){
        this.status=status;
        this.message=message;
    }
}
