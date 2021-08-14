package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;


@Data
public class User {
    @JsonIgnore
    private Long id;
    @NotNull
    @NotNull
    private String portalAccount;
    @JsonIgnore
    @NotNull
    private String password;
    @NotNull
    private String nickname;
    @NotNull
    @Size(min =  1)
    private List<Major> majorEntityList;
    private Long majorCount;
    @NotNull
    @Size(min =  1)
    private List<Address> addressEntities;
    private Timestamp created_at;
    private Timestamp updated_at;
}
