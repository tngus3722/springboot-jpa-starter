package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Setter
@Getter
public class UserResponse {

    private Long id;
    private String portalAccount;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
