package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Address {
    @ApiModelProperty(hidden = true)
    private Long id;
    private String address;
    @ApiModelProperty(hidden = true)
    private Timestamp created_at;
    @ApiModelProperty(hidden = true)
    private Timestamp updated_at;
}
