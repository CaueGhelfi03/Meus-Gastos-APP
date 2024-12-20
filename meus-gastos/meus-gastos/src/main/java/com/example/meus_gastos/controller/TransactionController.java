package com.example.meus_gastos.controller;

import com.example.meus_gastos.DTOs.Transaction.CreateTransactionDTO;
import com.example.meus_gastos.DTOs.Transaction.ResponseTransactionDTO;
import com.example.meus_gastos.domain.Transactions.TransactionsEntity;
import com.example.meus_gastos.mapper.Transaction.TransactionMap;
import com.example.meus_gastos.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transactions", description = "Controller for transactions operations")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionMap transactionMap;

    @PostMapping
    @Operation(summary = "Create a new transaction", description = "This operation is for registered a new transaction")
    @ApiResponse(responseCode = "201", description = "The transaction was registered with successful in system")
    @ApiResponse(responseCode = "400")
    public ResponseEntity<ResponseTransactionDTO> createTransaction (@RequestBody CreateTransactionDTO transaction) throws Exception {
        TransactionsEntity transactionsEntity = transactionMap.toTransactionEntity(transaction);
        CreateTransactionDTO transactionDTO = transactionService.createTransaction(transactionsEntity);
        ResponseTransactionDTO responseDTO = transactionMap.toResponseDTO(transactionDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Gell all transactions", description = "This operation return all the transactions")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<List<ResponseTransactionDTO>> getAllTransaction(){
        List<ResponseTransactionDTO> transactionsDTO = transactionService.getAllTransactions();
        if(transactionsDTO.isEmpty()) return new ResponseEntity<>(transactionsDTO, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(transactionsDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a transaction by id ", description = "This operation return a transaction by id")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404")
    public ResponseEntity<ResponseTransactionDTO> getTransactionById(Long id) throws Exception {
        ResponseTransactionDTO transactionDTO = transactionService.getTransactionById(id);
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a exists transaction", description = "This operation delete a exists transaction by id")
    @ApiResponse(responseCode = "204")
    @ApiResponse(responseCode = "404")
    public ResponseEntity<Void> deleteTrasanction(Long id) throws Exception{
        transactionService.deleteTransaction(id);
        return ResponseEntity.status(204).build();
    }

}
