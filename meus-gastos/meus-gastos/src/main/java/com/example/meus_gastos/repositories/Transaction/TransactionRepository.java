package com.example.meus_gastos.repositories.Transaction;

import com.example.meus_gastos.domain.Transactions.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionsEntity, Long> {

    Optional<TransactionsEntity> findById (Long id);
}
