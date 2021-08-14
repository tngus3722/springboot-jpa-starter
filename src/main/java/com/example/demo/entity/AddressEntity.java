package com.example.demo.entity;

import com.example.demo.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "address", schema = "jpatest", catalog = "")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String address;
    @JoinColumn(name = "user_id")
    @ManyToOne
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
