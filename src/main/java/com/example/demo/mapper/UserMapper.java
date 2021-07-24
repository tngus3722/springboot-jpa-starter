package com.example.demo.mapper;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper extends MajorMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    User toUser(UserEntity userEntity);
}
