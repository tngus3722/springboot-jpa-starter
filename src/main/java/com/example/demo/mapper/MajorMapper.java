package com.example.demo.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MajorMapper {

    MajorMapper INSTANCE = Mappers.getMapper(MajorMapper.class);
}
