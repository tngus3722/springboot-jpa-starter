package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
