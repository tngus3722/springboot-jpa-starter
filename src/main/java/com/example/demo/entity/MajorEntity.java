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
@Table(name = "major", schema = "jpatest", catalog = "")
public class MajorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String major;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @Basic
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp created_at;
    @Basic
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updated_at;

    public MajorEntity(Major major, UserEntity userEntity){
        this.major = major.getMajor();
        this.userEntity = userEntity;
    }
}
