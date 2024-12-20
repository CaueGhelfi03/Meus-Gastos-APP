package com.example.meus_gastos.domain.User;

import com.example.meus_gastos.domain.Card.CardEntity;
import com.example.meus_gastos.domain.ExpenseCategory.CategoryEntity;
import com.example.meus_gastos.domain.Transactions.TransactionsEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "user")
@Table(name = "User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Size(min = 3, max = 150)
    private String firstName;

    @Size(min = 3, max = 150)
    private String lastName;

    @Size(min = 10, max = 150)
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String document;

    @Size(min = 8)
    private String password;
    private String phone;

    private BigDecimal totalBalance;

    @OneToMany
    @JoinColumn(name = "categorie_id")
    private List<CategoryEntity> categories;

    @OneToMany
    @JoinColumn(name = "transaction_id")
    private List<TransactionsEntity> transactions;

    @OneToMany
    @JoinColumn(name = "card_id")
    private List<CardEntity> cards;


}
