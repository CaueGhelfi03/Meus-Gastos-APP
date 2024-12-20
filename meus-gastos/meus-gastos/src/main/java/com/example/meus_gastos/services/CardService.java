package com.example.meus_gastos.services;

import com.example.meus_gastos.DTOs.CreateCardDTO;
import com.example.meus_gastos.DTOs.ResponseCardDTO;
import com.example.meus_gastos.DTOs.Transaction.CreateTransactionDTO;
import com.example.meus_gastos.DTOs.Transaction.ResponseTransactionDTO;
import com.example.meus_gastos.domain.Card.CardEntity;
import com.example.meus_gastos.mapper.CardMap;
import com.example.meus_gastos.repositories.Card.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardMap cardMap;

    public List<ResponseCardDTO> getAllCard(){
        List<CardEntity> cardEntity = cardRepository.findAll();
        List<ResponseCardDTO> cards = new ArrayList<>();
        for(CardEntity cardActual : cardEntity){
        ResponseCardDTO cardDTO = cardMap.toResponseDTO(cardActual);
        cards.add(cardDTO);
        }
        return cards;
    }

    public ResponseCardDTO getCardById(Long id){
        Optional<CardEntity> cardEntity = cardRepository.findById(id);
        if (cardEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return cardMap.toResponseDTO(cardEntity.get());
    }

    public ResponseCardDTO createCard(CreateCardDTO card){
        CardEntity cardEntity = cardMap.toCardEntity(card);
        if(cardRepository.existsById(cardEntity.getId())) throw new ResponseStatusException(HttpStatus.CONFLICT);

        return cardMap.toResponseDTO(cardEntity);
    }

    public void deleteCard(Long id){
        ResponseCardDTO card = getCardById(id);
        CardEntity cardEntity = cardMap.toCardEntity(card);
        cardRepository.deleteById(cardEntity.getId());
    }





}
