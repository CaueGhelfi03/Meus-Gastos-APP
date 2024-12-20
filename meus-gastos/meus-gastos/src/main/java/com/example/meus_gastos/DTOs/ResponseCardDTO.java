package com.example.meus_gastos.DTOs;

import com.example.meus_gastos.domain.CardType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCardDTO {

    private String cardName;

    private String brand;

    private BigDecimal creditLimit;
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

}
