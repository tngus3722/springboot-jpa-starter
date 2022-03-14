package com.example.demo.entity;

import com.example.demo.model.Major;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "major")
public class MajorEntity extends BaseEntity{

    private String major;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public MajorEntity(Major major, UserEntity userEntity){
        this.major = major.getMajor();
        this.userEntity = userEntity;
    }
}
