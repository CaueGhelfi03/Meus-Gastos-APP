package com.example.meus_gastos.mapper.User;

import com.example.meus_gastos.DTOs.User.CreateUserDTO;
import com.example.meus_gastos.DTOs.User.UserUpdateDTO;
import com.example.meus_gastos.domain.User.UserEntity;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface UserMap {

    CreateUserDTO toCreateUserDTO(UserEntity user);

    UserEntity toUserEntity (CreateUserDTO user);

    UserUpdateDTO toUpdateUserDTO(UserEntity user);

    UserEntity toUserEntity (UserUpdateDTO user);
}
