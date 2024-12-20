package com.example.meus_gastos.DTOs.Transaction;

import com.example.meus_gastos.domain.PaymentMethods;
import com.example.meus_gastos.domain.User.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionDTO {


    private BigDecimal mount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd/HH:mm:ss", timezone = "America/Sao_Paulo")
    private LocalDateTime timeStamp;

    private Integer numberOfInstallments;
    private BigDecimal installmentValues;
    private BigDecimal interestRate;
    private String receivingAccount;
    private String description;
    private PaymentMethods paymentMethod;

}
