package org.example.budget.api;

import org.app.budget.api.BudgetController;
import org.app.budget.api.BudgetCreateDTO;
import org.app.budget.api.BudgetDTO;
import org.app.budget.domain.Budget;
import org.app.budget.service.BudgetService;
import org.app.config.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BudgetControllerTests {
    @Mock
    private BudgetService service;

    private BudgetController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new BudgetController(service);
    }

    private UserPrincipal createFakePrincipal() {
        return new UserPrincipal(
                UUID.randomUUID(),
                "testuser",
                "USER"
        );
    }

    @Test
    void slaBudgetOp_shouldReturnCreated() throws Exception {
        BudgetCreateDTO dto = new BudgetCreateDTO();
        Budget budget = dto.naarBudget();
        UserPrincipal user = createFakePrincipal();

        when(service.maakBudget(any(Budget.class), any(UUID.class)))
                .thenReturn(budget);

        ResponseEntity<BudgetDTO> response = controller.maakBudget(dto, user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(service).maakBudget(any(Budget.class), any(UUID.class));
    }
}
