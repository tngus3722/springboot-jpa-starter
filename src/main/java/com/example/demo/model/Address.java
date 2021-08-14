package com.example.demo.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Address {
    private Long id;
    private String address;
    private Timestamp created_at;
    private Timestamp updated_at;
}
