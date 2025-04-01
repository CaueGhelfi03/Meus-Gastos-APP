package com.example.meus_gastos.services;

import com.example.meus_gastos.DTOs.CreateCardDTO;
import com.example.meus_gastos.DTOs.ResponseCardDTO;
import com.example.meus_gastos.domain.Card.CardEntity;
import com.example.meus_gastos.domain.CardType;
import com.example.meus_gastos.mapper.CardMap;
import com.example.meus_gastos.repositories.Card.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private CardMap cardMap;

    @InjectMocks
    private CardService cardService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createCardCase1() {
        CreateCardDTO dto = new CreateCardDTO();
        dto.setCardName("CARTÃO NUBANK");
        dto.setCardType(CardType.CREDIT_CARD);
        dto.setBrand("VISA");
        dto.setBalance(new BigDecimal(200));
        dto.setCreditLimit(new BigDecimal(2000));

        CardEntity cardEntity = cardMap.toCardEntity(dto);

        when(cardMap.toCardEntity(dto)).thenReturn(cardEntity);
        when(cardRepository.save(any(CardEntity.class))).thenReturn(cardEntity);

        ResponseCardDTO createdCard = cardService.createCard(dto);

        verify(cardRepository, times(1)).save(any(CardEntity.class));

        assertNotNull(createdCard);
        assertEquals(cardEntity.getCardName(), createdCard.getCardName());
        assertEquals(cardEntity.getBalance(), createdCard.getBalance());
        assertEquals(cardEntity.getBrand(), createdCard.getBrand());

    }
}