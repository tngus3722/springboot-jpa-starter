package com.example.demo.dto.request;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserSignUpRequest {

    @ApiModelProperty(hidden = true)
    private Long id;
    @NotNull
    @Size(min =1, max = 100, message = "")
    private String portalAccount;
    @NotNull
    @Size(min =1, message = "")
    private String password;
    @NotNull
    @Size(min =1, message = "")
    private String nickname;
}
