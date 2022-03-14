package com.example.demo.dto.request;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserUpdateRequest {

    @Size(min =1, message = "")
    private String portalAccount;
    @Size(min =1, message = "")
    private String password;
    @Size(min =1, message = "")
    private String nickname;
}
