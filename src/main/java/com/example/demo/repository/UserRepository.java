package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import com.example.demo.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /*
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "delete from UserEntity u where u.id = ?1")
    void deleteUserByUserId(Long id);
     */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "delete from UserEntity u where u = ?1")
    void deleteUserByUserEntity(UserEntity userEntity);


    //@Query(value =" select new UserWrapper(u, (select count(*) from MajorEntity m where m.userEntity = u)) from UserEntity u where u.id = ?1")
    @Query(value = "select (select count(*) from MajorEntity m where m.userEntity = u) as t from UserEntity u where u.id = ?1")
    Long getUserWrapper(Long id);


    @Query(value = "select new com.example.demo.wrapper.UserWrapper(u, (select count(*) from MajorEntity m where m.userEntity = u) as t) from UserEntity u where u.id = ?1")
    UserWrapper test(Long id);
}
