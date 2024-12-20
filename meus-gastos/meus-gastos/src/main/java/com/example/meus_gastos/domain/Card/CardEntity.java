package com.example.meus_gastos.domain.Card;

import com.example.meus_gastos.domain.CardType;
import com.example.meus_gastos.domain.PaymentMethods;
import com.example.meus_gastos.domain.User.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Entity (name = "card")
@Table (name = "card")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (of = "id")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

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
