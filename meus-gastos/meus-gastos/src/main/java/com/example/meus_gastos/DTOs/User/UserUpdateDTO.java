package com.example.meus_gastos.DTOs.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String document;
    private String password;
    private String phone;

}
