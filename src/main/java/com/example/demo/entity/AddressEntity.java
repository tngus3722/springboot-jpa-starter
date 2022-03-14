package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "address")
public class AddressEntity extends BaseEntity {
    // Address CRUD는 여러분이 만들어보세요
    @Column(name = "address")
    private String address;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;

}
