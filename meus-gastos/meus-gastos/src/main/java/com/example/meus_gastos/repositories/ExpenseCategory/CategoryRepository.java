package com.example.meus_gastos.repositories.ExpenseCategory;

import com.example.meus_gastos.domain.ExpenseCategory.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findById (Long id);
}
