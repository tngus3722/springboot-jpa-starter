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
@Table(name = "address", schema = "jpatest", catalog = "")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "address")
    private String address;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;
    @Basic
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp created_at;
    @Basic
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updated_at;

    public AddressEntity(Address address, UserEntity userEntity){
        this.address = address.getAddress();
        this.userEntity = userEntity;
    }
}
