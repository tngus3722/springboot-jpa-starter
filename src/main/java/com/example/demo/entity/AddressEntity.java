package com.example.demo.entity;

import com.example.demo.model.Address;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "address")
public class AddressEntity extends BaseEntity {

    private Long id;
    @Column(name = "address")
    private String address;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;


    public AddressEntity(Address address, UserEntity userEntity) {
        this.address = address.getAddress();
        this.userEntity = userEntity;
    }
}
