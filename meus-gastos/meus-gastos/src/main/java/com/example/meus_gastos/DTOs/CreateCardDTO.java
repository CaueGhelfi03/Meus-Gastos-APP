package com.example.meus_gastos.DTOs;

import com.example.meus_gastos.domain.CardType;
import com.example.meus_gastos.domain.User.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCardDTO {

    @Size(max = 50)
    private String cardName;

    @Column(nullable = false, name = "card_brand")
    private String brand;

    private BigDecimal creditLimit;

    @Column(name = "card_balance")
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType cardType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


}
