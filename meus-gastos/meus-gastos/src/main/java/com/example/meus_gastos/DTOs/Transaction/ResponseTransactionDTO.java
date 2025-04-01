package com.example.meus_gastos.DTOs.Transaction;

import com.example.meus_gastos.domain.PaymentMethods;
import com.example.meus_gastos.domain.User.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTransactionDTO {

    private LocalDateTime timeStamp;
    private BigDecimal mount;
    private PaymentMethods paymentMethod;
    private Integer numberOfInstallments;
    private BigDecimal installmentValues;
    private String receivingAccount;
    private String description;


    public ResponseTransactionDTO(LocalDateTime timeStamp, BigDecimal mount, String receivingAccount, PaymentMethods paymentMethod, Integer numberOfInstallments, BigDecimal installmentValues, String receivingAccount1, String description) {
    }
}
