package com.example.meus_gastos.repositories.User;

import com.example.meus_gastos.domain.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById (Long id);
    Optional<UserEntity> findByDocument (String document);
    boolean existsByDocument(String document);

}
