package com.example.demo.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserSignUpRequest {

    @NotNull
    @Size(min = 1, max = 100, message = "")
    private String portalAccount;
    @NotNull
    @Size(min = 1, message = "")
    private String password;
    @NotNull
    @Size(min = 1, message = "")
    private String nickname;
}
