package com.example.meus_gastos.services;

import com.example.meus_gastos.DTOs.Transaction.ResponseTransactionDTO;
import com.example.meus_gastos.domain.PaymentMethods;
import com.example.meus_gastos.domain.Transactions.TransactionsEntity;
import com.example.meus_gastos.mapper.Transaction.TransactionMap;
import com.example.meus_gastos.repositories.Transaction.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private UserService userService;

    @Mock
    private TransactionMap transactionMap;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Get all transactions")
    void getAllTransactionsCase1() {
        TransactionsEntity transaction1 = new TransactionsEntity();
        transaction1.setTimeStamp(LocalDateTime.now());
        transaction1.setMount(new BigDecimal("100.00"));
        transaction1.setReceivingAccount("Amazon");
        transaction1.setPaymentMethod(PaymentMethods.CARD);
        transaction1.setNumberOfInstallments(3);
        transaction1.setInstallmentValues(new BigDecimal("33.33"));
        transaction1.setDescription("Payment for services");

        TransactionsEntity transaction2 = new TransactionsEntity();
        transaction2.setTimeStamp(LocalDateTime.now());
        transaction2.setMount(new BigDecimal("120.00"));
        transaction2.setReceivingAccount("Shopee");
        transaction2.setPaymentMethod(PaymentMethods.PIX);
        transaction2.setNumberOfInstallments(1);
        transaction2.setInstallmentValues(new BigDecimal("120.00"));
        transaction2.setDescription("Payment for services");

        List<TransactionsEntity> transactionsEntities = List.of(transaction1,transaction2);

        when(repository.findAll()).thenReturn(transactionsEntities);

        ResponseTransactionDTO dto1 = new ResponseTransactionDTO(
                transaction1.getTimeStamp(),
                transaction1.getMount(),
                transaction1.getReceivingAccount(),
                transaction1.getPaymentMethod(),
                transaction1.getNumberOfInstallments(),
                transaction1.getInstallmentValues(),
                transaction1.getReceivingAccount(),
                transaction1.getDescription()
        );

        ResponseTransactionDTO dto2 = new ResponseTransactionDTO(
                transaction2.getTimeStamp(),
                transaction2.getMount(),
                transaction2.getReceivingAccount(),
                transaction2.getPaymentMethod(),
                transaction2.getNumberOfInstallments(),
                transaction2.getInstallmentValues(),
                transaction2.getReceivingAccount(),
                transaction2.getDescription()
        );

        when(transactionMap.toResponseDTO(transaction1)).thenReturn(dto1);

        when(transactionMap.toResponseDTO(transaction2)).thenReturn(dto2);

        List<ResponseTransactionDTO> responseTransactionDTOS = transactionService.getAllTransactions();

        verify(repository, times(1)).findAll();

        verify(transactionMap, times(2)).toResponseDTO(any(TransactionsEntity.class));

        assertNotNull(responseTransactionDTOS);
        assertEquals(2,responseTransactionDTOS.size());

        assertEquals(dto1.getMount(), responseTransactionDTOS.get(0).getMount());
        assertEquals(dto1.getDescription(), responseTransactionDTOS.get(0).getDescription());

        assertEquals(dto2.getMount(), responseTransactionDTOS.get(1).getMount());
        assertEquals(dto2.getDescription(), responseTransactionDTOS.get(1).getDescription());

    }

    @Test
    @DisplayName("Should return a empty list when no transactions are found in the database")
    void getAllTransactionsCase2(){

        when(repository.findAll()).thenReturn(new ArrayList<>());

        List<ResponseTransactionDTO> responseTransactionDTOS = transactionService.getAllTransactions();

        verify(repository,times(1)).findAll();
        assertNotNull(responseTransactionDTOS);
        assertTrue(responseTransactionDTOS.isEmpty());
    }

    @Test
    @DisplayName("Should return transactions by id")
    void getTransactionByIdCase1() throws Exception {
        TransactionsEntity transaction1 = new TransactionsEntity();
        transaction1.setId(1L);
        transaction1.setTimeStamp(LocalDateTime.now());
        transaction1.setMount(new BigDecimal("100.00"));
        transaction1.setReceivingAccount("Amazon");
        transaction1.setPaymentMethod(PaymentMethods.CARD);
        transaction1.setNumberOfInstallments(3);
        transaction1.setInstallmentValues(new BigDecimal("33.33"));
        transaction1.setDescription("Payment for services");

        when(repository.findById(1L)).thenReturn(Optional.of(transaction1));

        ResponseTransactionDTO dto1 = new ResponseTransactionDTO(
                transaction1.getTimeStamp(),
                transaction1.getMount(),
                transaction1.getReceivingAccount(),
                transaction1.getPaymentMethod(),
                transaction1.getNumberOfInstallments(),
                transaction1.getInstallmentValues(),
                transaction1.getReceivingAccount(),
                transaction1.getDescription()
        );

        when(transactionMap.toResponseDTO(transaction1)).thenReturn(dto1);

        ResponseTransactionDTO result = transactionService.getTransactionById(1L);

        verify(repository,times(1)).findById(1L);
        verify(transactionMap,times(1)).toResponseDTO(transaction1);

        assertNotNull(result);
        assertEquals(dto1.getMount(), result.getMount());
        assertEquals(dto1.getDescription(), result.getDescription());
        assertEquals(dto1.getReceivingAccount(), result.getReceivingAccount());
    }

    @Test
    @DisplayName("Should thrown exception when transaction not foud by ID ")
    void getTransactionByIdCase2(){
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ChangeSetPersister.NotFoundException.class, () ->{
            transactionService.getTransactionById(1L);
        });
        verify(repository,times(1)).findById(1L);
    }

}