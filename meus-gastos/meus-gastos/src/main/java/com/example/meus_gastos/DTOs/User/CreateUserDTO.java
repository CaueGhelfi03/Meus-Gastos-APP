package com.example.meus_gastos.DTOs.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateUserDTO{

        private String firstName;
        private String lastName;
        private String email;
        private String document;
        private String password;
        private String phone;
        private BigDecimal totalBalance;
}
