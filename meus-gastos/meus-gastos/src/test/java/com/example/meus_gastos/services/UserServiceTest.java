package com.example.meus_gastos.services;

import com.example.meus_gastos.DTOs.User.CreateUserDTO;
import com.example.meus_gastos.DTOs.User.ResponseUserDTO;
import com.example.meus_gastos.domain.User.UserEntity;
import com.example.meus_gastos.mapper.User.UserMap;
import com.example.meus_gastos.repositories.User.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMap userMap;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createUserCase1() throws Exception {

        CreateUserDTO userDTO = new CreateUserDTO("Caue", "Ghelfi", "caueghelfi@gmail.com", "12345678900", "senha123", "123-1234", new BigDecimal(10));

        UserEntity user = new UserEntity();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setDocument(userDTO.getDocument());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setTotalBalance(userDTO.getTotalBalance());
        user.setId(1L);

        when(userMap.toUserEntity(userDTO)).thenReturn(user);

        when(repository.existsByDocument(user.getDocument())).thenReturn(false);
        when(repository.save(any(UserEntity.class))).thenReturn(user);

        ResponseUserDTO expectedResponse = new ResponseUserDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getDocument()
        );

        ResponseUserDTO createdUser = userService.createUser(userDTO);

        verify(repository,times(1)).save(user);

        assertNotNull(createdUser);
        assertEquals(expectedResponse.getDocument(), createdUser.getDocument());
        assertEquals(expectedResponse.getEmail(), createdUser.getEmail());
        assertEquals(expectedResponse.getFirstName(), createdUser.getFirstName());
    }

    @Test
    void findByIdCase1() {




    }
}