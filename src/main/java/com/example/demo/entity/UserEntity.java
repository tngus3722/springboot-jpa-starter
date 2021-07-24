package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", schema = "jpatest", catalog = "")
public class UserEntity {
    private Long id;
    private String portalAccount;
    private String password;
    private String nickname;
    private List<MajorEntity> majorEntityList;
    private Long majorCount;

    public UserEntity(UserEntity userEntity, Long count){
        this.id = userEntity.id;
        this.portalAccount = userEntity.getPortalAccount();
        this.password = userEntity.getPassword();
        this.nickname = userEntity.getNickname();
        this.majorEntityList = userEntity.getMajorEntityList();
        this.majorCount = count;
    }

    @Transient
    public Long getMajorCount() {
        return majorCount;
    }

    public void setMajorCount(Long majorCount) {
        this.majorCount = majorCount;
    }

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<MajorEntity> getMajorEntityList() {
        return majorEntityList;
    }

    public void setMajorEntityList(List<MajorEntity> majorEntityList) {
        this.majorEntityList = majorEntityList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "portal_account")
    public String getPortalAccount() {
        return portalAccount;
    }

    public void setPortalAccount(String portalAccount) {
        this.portalAccount = portalAccount;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(portalAccount, that.portalAccount) && Objects.equals(password, that.password) && Objects.equals(nickname, that.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, portalAccount, password, nickname);
    }
}
