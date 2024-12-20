package com.example.meus_gastos.mapper;

import com.example.meus_gastos.DTOs.CreateCardDTO;
import com.example.meus_gastos.DTOs.ResponseCardDTO;
import com.example.meus_gastos.domain.Card.CardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMap {


    ResponseCardDTO toResponseDTO (CardEntity card);
    CardEntity toCardEntity (CreateCardDTO cardDTO);

    CardEntity toCardEntity (ResponseCardDTO cardDTO);



}
