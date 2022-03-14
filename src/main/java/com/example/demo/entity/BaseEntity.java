package com.example.demo.entity;

import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
@Setter
@Getter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;
    @Basic
    @CreatedDate
    @Column(name = "created_at", insertable = false, updatable = false)
    protected LocalDateTime createdAt;
    @Basic
    @LastModifiedDate
    @Column(name = "updated_at", insertable = false, updatable = false)
    protected LocalDateTime updatedAt;

}
