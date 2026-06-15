package org.example.transactie.service;

import org.app.Account.infrastructure.UserDBO;
import org.app.Account.service.AuthorizationService;
import org.app.budget.domain.Budget;
import org.app.categorie.repository.CategorieDBO;
import org.app.categorie.service.CategorieService;
import org.app.config.Exceptions.EntityNotFoundException;
import org.app.events.TransactieAangemaaktEvent;
import org.app.transaction.domain.Transactie;
import org.app.transaction.repository.TransactieDBO;
import org.app.transaction.repository.TransactieRepository;
import org.app.transaction.service.TransactieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class TransactieServiceTest {
    @Mock
    private TransactieRepository transactieRepository;

    @Mock
    private AuthorizationService authorizationService;

    @Mock
    private CategorieService categorieService;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private TransactieService transactieService;

    @Test
    void slaTransactieOp_ZonderCategorie_SlaatOpEnPubliceertEvent() {
        UUID userId = UUID.randomUUID();

        Transactie transactie = mock(Transactie.class);
        UserDBO user = mock(UserDBO.class);
        TransactieDBO dbo = mock(TransactieDBO.class);
        TransactieDBO saved = mock(TransactieDBO.class);

        UUID transactieId = UUID.randomUUID();

        when(transactie.getCategorieID()).thenReturn(null);
        when(authorizationService.leesUserDBO(userId)).thenReturn(user);
        when(transactie.naarDBO(user, null)).thenReturn(dbo);
        when(transactieRepository.save(dbo)).thenReturn(saved);
        when(saved.getId()).thenReturn(transactieId);

        transactieService.slaTransactieOp(transactie, userId);

        verify(authorizationService).leesUserDBO(userId);
        verify(categorieService, never()).leesCategorieDBO(any());
        verify(transactieRepository).save(dbo);
        verify(eventPublisher).publishEvent(any(TransactieAangemaaktEvent.class));
    }

    @Test
    void slaTransactieOp_MetCategorie_HaaltCategorieOp() {
        UUID userId = UUID.randomUUID();
        UUID categorieId = UUID.randomUUID();

        Transactie transactie = mock(Transactie.class);
        UserDBO user = mock(UserDBO.class);
        CategorieDBO categorie = mock(CategorieDBO.class);
        TransactieDBO dbo = mock(TransactieDBO.class);
        TransactieDBO saved = mock(TransactieDBO.class);

        when(transactie.getCategorieID()).thenReturn(categorieId);
        when(authorizationService.leesUserDBO(userId)).thenReturn(user);
        when(categorieService.leesCategorieDBO(categorieId)).thenReturn(categorie);
        when(transactie.naarDBO(user, categorie)).thenReturn(dbo);
        when(transactieRepository.save(dbo)).thenReturn(saved);
        when(saved.getId()).thenReturn(UUID.randomUUID());

        transactieService.slaTransactieOp(transactie, userId);

        verify(categorieService).leesCategorieDBO(categorieId);
        verify(transactieRepository).save(dbo);
    }

    @Test
    void leesTotaalVanTransactiesBijBudget_GeeftTotaalTerug() {
        UUID userId = UUID.randomUUID();
        Budget budget = mock(Budget.class);

        BigDecimal expected = new BigDecimal("125.50");

        when(budget.getBeginDatum()).thenReturn(LocalDate.of(2026, 6, 1));
        when(budget.getEindDatum()).thenReturn(LocalDate.of(2026, 6, 30));

        when(transactieRepository.totaalTransactiesTussenPeriodes(
                eq(userId), any(), any()))
                .thenReturn(expected);

        BigDecimal result =
                transactieService.leesTotaalVanTransactiesBijBudget(userId, budget);

        assertEquals(expected, result);
    }

    @Test
    void leesTransactieDBO_Bestaat_GeeftTransactieTerug() {
        UUID id = UUID.randomUUID();

        TransactieDBO dbo = mock(TransactieDBO.class);

        when(transactieRepository.findById(id))
                .thenReturn(Optional.of(dbo));

        TransactieDBO result =
                transactieService.leesTransactieDBO(id);

        assertSame(dbo, result);
    }

    @Test
    void leesTransactieDBO_BestaatNiet_GooitException() {
        UUID id = UUID.randomUUID();

        when(transactieRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> transactieService.leesTransactieDBO(id)
        );
    }

    @Test
    void leesTransacties_MaptNaarDomainObjecten() {
        UUID userId = UUID.randomUUID();

        TransactieDBO dbo1 = mock(TransactieDBO.class);
        TransactieDBO dbo2 = mock(TransactieDBO.class);

        when(transactieRepository.findByUser_Id(userId))
                .thenReturn(List.of(dbo1, dbo2));

        List<Transactie> result =
                transactieService.leesTransacties(userId);

        assertEquals(2, result.size());
    }

    @Test
    void leesTransactiesTussenTijden_GeeftResultatenTerug() {
        UUID userId = UUID.randomUUID();

        Budget budget = mock(Budget.class);

        when(budget.getBeginDatum()).thenReturn(LocalDate.of(2026, 6, 1));
        when(budget.getEindDatum()).thenReturn(LocalDate.of(2026, 6, 30));

        when(transactieRepository.findByUser_IdAndCreatieDatumBetween(
                eq(userId), any(), any()))
                .thenReturn(List.of());

        List<Transactie> result =
                transactieService.leesTransactiesTussenTijden(userId, budget);

        assertNotNull(result);

        verify(transactieRepository)
                .findByUser_IdAndCreatieDatumBetween(
                        eq(userId),
                        any(),
                        any()
                );
    }

}
