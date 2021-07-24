package com.example.demo.wrapper;

import com.example.demo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserWrapper {
    private Long id;
    private String portalAccount;
    private Long majorCount;

    public UserWrapper(UserEntity userEntity, Long majorCount){
        this.id = userEntity.getId();
        this.portalAccount = userEntity.getPortalAccount();
        this.majorCount = majorCount;
    }

}
