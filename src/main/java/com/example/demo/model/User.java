package com.example.demo.model;

import com.example.demo.entity.MajorEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
public class User {
    private Long id;
    private String portalAccount;
    private String password;
    private String nickname;
    private List<Major> majorEntityList;
    private Long majorCount;
    private List<Address> addressEntities;

}
