package org.example.transactie.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.transaction.api.TransactieController;
import org.transaction.api.TransactieCreateDTO;
import org.transaction.api.TransactieDTO;
import org.transaction.domain.Transactie;
import org.transaction.service.TransactieService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TransactieControllerTests {

    @Mock
    private TransactieService service;

    private TransactieController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new TransactieController(service);
    }

    @Test
    void slaTransactieOp_shouldReturnCreated() throws Exception {
        TransactieCreateDTO dto = new TransactieCreateDTO();
        Transactie transactie = dto.naarTransactie();

        // Mock service to return a completed future
        when(service.slaTransactieOp(any(Transactie.class)))
                .thenReturn(transactie);

        ResponseEntity<Void> response = controller.slaTransactieOp(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(service).slaTransactieOp(any(Transactie.class));
    }

    @Test
    void leesTransacties_shouldReturnDTOList() throws Exception {
        Transactie t1 = new Transactie(new BigDecimal("10.00"), "Test 1", LocalDateTime.now());
        Transactie t2 = new Transactie(new BigDecimal("20.00"), "Test 2", LocalDateTime.now());
        List<Transactie> mockTransacties = List.of(t1, t2);

        when(service.LeesTransacties())
                .thenReturn((mockTransacties));

        List<TransactieDTO> dtos = controller.leesTransacties();

        assertEquals(mockTransacties.size(), dtos.size());
        for (int i = 0; i < dtos.size(); i++) {
            assertEquals(mockTransacties.get(i).getId(), dtos.get(i).getId());
        }
        verify(service).LeesTransacties();
    }
}
