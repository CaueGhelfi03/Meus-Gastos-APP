package com.example.meus_gastos.repositories.Card;

import com.example.meus_gastos.domain.Card.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, Long> {

    Optional<CardEntity> findById (Long id);

}
