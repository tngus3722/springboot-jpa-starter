package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
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
    @OneToMany(mappedBy = "userEntity" ,orphanRemoval = true , cascade = CascadeType.ALL)
    private List<AddressEntity> addressEntities;
    @Formula("(select count(*) from major m where m.user_id = id)")
    private Long majorCount;
}
