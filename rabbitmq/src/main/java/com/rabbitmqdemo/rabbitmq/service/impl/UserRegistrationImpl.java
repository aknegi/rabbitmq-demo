package com.rabbitmqdemo.rabbitmq.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmqdemo.rabbitmq.dao.UserRegistrationDao;
import com.rabbitmqdemo.rabbitmq.dto.DemoDto;
import com.rabbitmqdemo.rabbitmq.dto.PortalResponse;
import com.rabbitmqdemo.rabbitmq.dto.UserDto;
import com.rabbitmqdemo.rabbitmq.model.User;
import com.rabbitmqdemo.rabbitmq.service.UserRegistration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserRegistrationImpl implements UserRegistration {

    @Autowired
    private UserRegistrationDao userRepository;

    @Autowired
    private RabbitTemplate template;


    @Override
    public PortalResponse register(UserDto userDto) {
        PortalResponse portalResponse = new PortalResponse("FAIL", "FAIL");
        User user = userRepository.findByEmail(userDto.getEmail());
        if (Objects.isNull(user)) {
            ObjectMapper mapper = new ObjectMapper();
            user = mapper.convertValue(userDto, User.class);
            String uuid = UUID.randomUUID().toString();
            user.setUuid(uuid);
            //  userRepository.save(user);
            portalResponse.setMessage("SUCCESS");
            portalResponse.setStatus("SUCCESS");
            JSONObject data = new JSONObject();
            data.put("uuid", uuid);
            portalResponse.setData(data);
            JSONObject emailContent = new JSONObject();
            emailContent.put("to", user.getEmail());
            emailContent.put("body", "Your are successfully Registered");
            emailContent.put("topic", "Registration Successful");
            //template.convertAndSend(RabbitMqConfig.EXCHANGE,
            // RabbitMqConfig.ROUTING_KEY, emailContent);
        } else {
            portalResponse.setData("Email Id already Exists");
        }
        return portalResponse;
    }

    @Override
    public PortalResponse getAllData() {
        PortalResponse portalResponse = new PortalResponse("FAIL", "FAIL");
        try {
            String url = "https://5ef99e4bbc5f8f0016c66d42.mockapi.io/testing/data";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            JSONParser parser = new JSONParser();
            JSONArray variations = (JSONArray) parser.parse(response.toString());
            List<DemoDto> responseList = new ArrayList<>();
            for (Object var : variations) {
                ObjectMapper mapper = new ObjectMapper();
                DemoDto variation = mapper.readValue(var.toString(), DemoDto.class);
                SimpleDateFormat formatter1 = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                Date createdDate = formatter1.parse(variation.getCreatedAt().toString());
                variation.setCreatedAt(createdDate);
                responseList.add(variation);
            }
            portalResponse.setData("SUCCESS");
            portalResponse.setMessage("SUCCESS");
            portalResponse.setData(responseList);
            return portalResponse;
        } catch (Exception e) {
            portalResponse.setData("Exception while reading data");
            System.out.println("Exception while reading data");
        }
        return portalResponse;
    }
}