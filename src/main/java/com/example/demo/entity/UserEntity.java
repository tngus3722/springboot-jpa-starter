package com.example.demo.entity;

import com.example.demo.model.Address;
import com.example.demo.model.Major;
import com.example.demo.model.User;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "user", schema = "jpatest", catalog = "")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
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
    @Formula("(select count(*) from major m where m.user_id = id)")
    private Long majorCount;
    @Basic
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp created_at;
    @Basic
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updated_at;


    public UserEntity(User user) {
        this.password = user.getPassword();
        this.updateUser(user);
    }

    public void updateUser(User user) {
        this.portalAccount = user.getPortalAccount();
        this.nickname = user.getNickname();

        if ( this.majorEntityList == null )
            this.majorEntityList = new ArrayList<>();
        else
            this.majorEntityList.clear();
        for (Major m : user.getMajorEntityList())
            this.majorEntityList.add(new MajorEntity(m, this));

        if ( this.addressEntities == null )
            this.addressEntities = new ArrayList<>();
        else
            this.addressEntities.clear();
        for (Address a : user.getAddressEntities())
            this.addressEntities.add(new AddressEntity(a, this));
    }
}
