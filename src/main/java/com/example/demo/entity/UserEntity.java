package com.example.demo.entity;

import com.example.demo.dto.request.UserSignUpRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.response.UserResponse;
import java.time.LocalDateTime;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Column(name = "portal_account")
    private String portalAccount;
    @Column(name = "password")
    private String password;
    @Column(name = "nickname")
    private String nickname;
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MajorEntity> majorEntityList;
    @OneToMany(mappedBy = "userEntity", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<AddressEntity> addressEntities;

    // static factory method pattern
    public static UserEntity from(UserSignUpRequest userSignUpRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPortalAccount(userSignUpRequest.getPortalAccount());
        userEntity.setPassword(userSignUpRequest.getPassword());
        userEntity.setNickname(userSignUpRequest.getNickname());
        return userEntity;
    }

    public void update(UserUpdateRequest userUpdateRequest) {
        if (StringUtils.isNotBlank(userUpdateRequest.getNickname())) {
            this.setNickname(userUpdateRequest.getNickname());
        }

        if (StringUtils.isNotBlank(userUpdateRequest.getPortalAccount())) {
            this.setPortalAccount(userUpdateRequest.getPortalAccount());
        }

        if (StringUtils.isNotBlank(userUpdateRequest.getPassword())) {
            this.setPassword(userUpdateRequest.getPassword());
        }
    }

}
