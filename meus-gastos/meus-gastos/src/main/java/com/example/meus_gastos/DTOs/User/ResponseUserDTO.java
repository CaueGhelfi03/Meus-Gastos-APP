package com.example.meus_gastos.DTOs.User;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String document;
    private String password;
    private String phone;
    private BigDecimal totalBalance;


    public ResponseUserDTO(String firstName, String lastName, String email, String document) {
    }
}
