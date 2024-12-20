package com.example.meus_gastos.mapper.Transaction;

import com.example.meus_gastos.DTOs.Transaction.CreateTransactionDTO;
import com.example.meus_gastos.DTOs.Transaction.ResponseTransactionDTO;
import com.example.meus_gastos.domain.Transactions.TransactionsEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface TransactionMap {

    CreateTransactionDTO toCreateTransactionDTO (TransactionsEntity transaction);
    TransactionsEntity toTransactionEntity(CreateTransactionDTO transactionDTO);
    ResponseTransactionDTO toResponseDTO (CreateTransactionDTO transaction);
    ResponseTransactionDTO toResponseDTO (TransactionsEntity transaction);

    TransactionsEntity toTransactionEntity(ResponseTransactionDTO transactionDTO);

    @AfterMapping
    default void setDefaultTimeStamp(@MappingTarget CreateTransactionDTO dto){
        if(dto.getTimeStamp() == null){
            dto.setTimeStamp(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        }
    }


}
