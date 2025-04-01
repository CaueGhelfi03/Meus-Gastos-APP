package com.example.meus_gastos.services;

import com.example.meus_gastos.DTOs.Transaction.CreateTransactionDTO;
import com.example.meus_gastos.DTOs.Transaction.ResponseTransactionDTO;
import com.example.meus_gastos.DTOs.User.UserUpdateDTO;
import com.example.meus_gastos.domain.Transactions.TransactionsEntity;
import com.example.meus_gastos.domain.User.UserEntity;
import com.example.meus_gastos.mapper.Transaction.TransactionMap;
import com.example.meus_gastos.mapper.User.UserMap;
import com.example.meus_gastos.repositories.Transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionMap transactioMap;

    @Autowired
    private UserMap usermap;

    public CreateTransactionDTO createTransaction (TransactionsEntity transaction) throws Exception {
        if(repository.existsById(transaction.getId())) throw new ResponseStatusException(HttpStatus.CONFLICT, "Transaction already exists");

        if(transaction.getTimeStamp() == null){
            transaction.setTimeStamp(LocalDateTime.now(ZoneId.systemDefault()));
        }else {
            transaction.setTimeStamp(transaction.getTimeStamp()
                    .atZone(ZoneId.systemDefault())  // Pega o fuso horário do sistema
                    .withZoneSameInstant(ZoneId.of("America/Sao_Paulo"))  // Converte para São Paulo
                    .toLocalDateTime());
        }

        UserEntity user = userService.findById(transaction.getSender().getId());
        user.setTotalBalance(user.getTotalBalance().subtract(transaction.getMount()));

        UserUpdateDTO userDTO = usermap.toUpdateUserDTO(user);

        userService.updateUser(user.getId(), userDTO);

        repository.save(transaction);
        return transactioMap.toCreateTransactionDTO(transaction);
    }

    public List<ResponseTransactionDTO> getAllTransactions(){
        List<TransactionsEntity> transactions = repository.findAll();
        List<ResponseTransactionDTO> transactionsDTOS = new ArrayList<>();
        for (TransactionsEntity actualTransaction : transactions ){
            ResponseTransactionDTO transactionDTO = transactioMap.toResponseDTO(actualTransaction);
            transactionsDTOS.add(transactionDTO);
        }
        return transactionsDTOS;
    }

    public ResponseTransactionDTO getTransactionById(Long id) throws Exception{
        Optional<TransactionsEntity> transaction = repository.findById(id);
        if(transaction.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        TransactionsEntity transactionsEntity = transaction.get();
        return transactioMap.toResponseDTO(transactionsEntity);
    }

    public void deleteTransaction(Long id) throws Exception {
        if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        ResponseTransactionDTO transaction = getTransactionById(id);
        TransactionsEntity transactionsEntity = transactioMap.toTransactionEntity(transaction);
        long monthsDifference = ChronoUnit.MONTHS.between(transactionsEntity.getTimeStamp(), LocalDateTime.now());
        if(monthsDifference >= 1){
            throw new RuntimeException("Its not possible to delete this transaction");
        }
        repository.deleteById(id);
    }


}
