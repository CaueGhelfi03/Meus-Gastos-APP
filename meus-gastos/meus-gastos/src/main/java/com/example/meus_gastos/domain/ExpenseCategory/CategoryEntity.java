package com.example.meus_gastos.domain.ExpenseCategory;

import com.example.meus_gastos.domain.User.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity (name = "category_name")
@Table (name = "category_name")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorie_id")
    private Long id;

    @Size(max = 20)
    @Column(name = "categorie_name")
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
