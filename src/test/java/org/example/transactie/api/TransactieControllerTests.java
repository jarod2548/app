package org.example.transactie.api;

import org.app.config.UserPrincipal;
import org.app.transaction.api.TransactieController;
import org.app.transaction.api.TransactieCreateDTO;
import org.app.transaction.api.TransactieDTO;
import org.app.transaction.domain.Transactie;
import org.app.transaction.service.TransactieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactieControllerTests {

    @Mock
    private TransactieService service;

    private TransactieController controller;

    @BeforeEach
    void setUp() {
        controller = new TransactieController(service);
    }

    private UserPrincipal createFakePrincipal() {
        return new UserPrincipal(
                UUID.randomUUID(),
                "testuser",
                "USER"
        );
    }

    @Test
    void slaTransactieOp_shouldCallService() {

        UserPrincipal user = createFakePrincipal();

        TransactieCreateDTO dto = new TransactieCreateDTO();
        dto.setAantal(new BigDecimal("100"));
        dto.setBeschrijving("test");

        controller.slaTransactieOp(dto, user);

        verify(service).slaTransactieOp(
                any(Transactie.class),
                eq(user.getId())
        );

        verifyNoMoreInteractions(service);
    }

    @Test
    void leesTransacties_shouldReturnListFromService() {

        UserPrincipal user = createFakePrincipal();

        Transactie t1 = new Transactie(new BigDecimal("10.00"), "Test 1", LocalDateTime.now());
        Transactie t2 = new Transactie(new BigDecimal("20.00"), "Test 2", LocalDateTime.now());

        when(service.leesTransacties(any(UUID.class))).thenReturn(List.of(t1, t2));

        List<TransactieDTO> result = controller.leesTransacties(user);

        assertEquals(2, result.size());

        verify(service).leesTransacties(user.getId());
        verifyNoMoreInteractions(service);
    }
}