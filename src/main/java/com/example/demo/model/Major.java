package com.example.demo.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Major {
    private Long id;
    private String major;
    private Timestamp created_at;
    private Timestamp updated_at;
}
