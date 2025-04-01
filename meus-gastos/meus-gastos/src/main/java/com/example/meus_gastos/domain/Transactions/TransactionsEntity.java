package com.example.meus_gastos.domain.Transactions;

import com.example.meus_gastos.domain.PaymentMethods;
import com.example.meus_gastos.domain.User.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity (name = "transactions")
@Table (name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "id")
public class TransactionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    private BigDecimal mount;

    private LocalDateTime timeStamp;

    private Integer numberOfInstallments;
    private BigDecimal installmentValues;
    private BigDecimal interestRate;
    private String receivingAccount;
    private String description;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    @Enumerated(EnumType.STRING)
    private PaymentMethods paymentMethod;


}
