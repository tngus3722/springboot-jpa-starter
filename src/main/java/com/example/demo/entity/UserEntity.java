package com.example.demo.entity;

import com.example.demo.model.Address;
import com.example.demo.model.Major;
import com.example.demo.model.User;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user", schema = "jpatest", catalog = "")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "portal_account")
    private String portalAccount;
    private String password;
    private String nickname;
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MajorEntity> majorEntityList;
    @OneToMany(mappedBy = "userEntity", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<AddressEntity> addressEntities;
    @Formula("(select count(*) from major m where m.user_id = id)")
    private Long majorCount;
    @Basic
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp created_at;
    @Basic
    @Column(name = "updated_at")
    private Timestamp updated_at;


    public UserEntity(User user) {
        this.updateUser(user);
    }

    public void updateUser(User user) {
        this.password = user.getPassword();
        this.portalAccount = user.getPortalAccount();
        this.nickname = user.getNickname();
        user.getMajorEntityList().clear();
        for (Major m : user.getMajorEntityList())
            this.majorEntityList.add(new MajorEntity(m, this));
        user.getAddressEntities().clear();
        for (Address a : user.getAddressEntities())
            this.addressEntities.add(new AddressEntity(a, this));
    }
}
