package com.example.demo.mapper;


import com.example.demo.entity.AddressEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.Address;
import com.example.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
    Address toAddress(AddressEntity addressEntity);
}
