package org.example.budget.api;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.app.budget.api.BudgetCreateDTO;
import org.app.budget.api.BudgetResponseDTO;
import org.app.budget.domain.Budget;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BudgetModelTests {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void BudgetDTO_ShouldFail_WhenAantalIsNull(){
        BudgetCreateDTO dto = new BudgetCreateDTO();
        dto.setAantal(null);
        dto.setNaam("naam");
        LocalDate datum = LocalDate.of(2000,1,1);
        dto.setBeginDatum(datum);
        dto.setEindDatum(datum);

        Set<ConstraintViolation<BudgetCreateDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("aantal")));
    }

    @Test
    void BudgetDTO_ShouldFail_WhenAantalIs0OrLess(){
        BudgetCreateDTO dto = new BudgetCreateDTO();
        dto.setAantal(BigDecimal.ZERO);
        dto.setNaam("naam");
        LocalDate datum = LocalDate.of(2000,1,1);
        dto.setBeginDatum(datum);
        dto.setEindDatum(datum);

        Set<ConstraintViolation<BudgetCreateDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("aantal")));
    }

    @Test
    void budgetDTO_shouldPassValidation_whenAllFieldsSet() {
        BudgetCreateDTO dto = new BudgetCreateDTO();
        dto.setAantal(BigDecimal.TEN);
        dto.setNaam("naam");
        LocalDate datum = LocalDate.of(2000,1,1);
        dto.setBeginDatum(datum);
        dto.setEindDatum(datum);

        Set<ConstraintViolation<BudgetCreateDTO>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void budgetDTO_shouldMapFromBudgetCorrectly() {
        Budget budget= new Budget(new BigDecimal("100"),
                "naam",
                LocalDate.of(2026, 3, 19),
                LocalDate.of(2026, 3, 19));

        BudgetResponseDTO dto = new BudgetResponseDTO(budget);

        assertEquals(budget.getId(), dto.getId());
        assertEquals(budget.getNaam(), dto.getNaam());
        assertEquals(budget.getAantal(), dto.getAantal());
        assertEquals(budget.getBeginDatum(), dto.getBeginDatum());
        assertEquals(budget.getEindDatum(), dto.getEindDatum());
    }

    @Test
    void budget_shouldMapFromBudgetCreateDTOCorrectly() {
        LocalDate datum = LocalDate.of(2026, 3, 19);
        BudgetCreateDTO dto = new BudgetCreateDTO();
        dto.setAantal(BigDecimal.TEN);
        dto.setNaam("naam");
        dto.setBeginDatum(datum);
        dto.setEindDatum(datum);

        Budget budget = dto.naarBudget();

        assertEquals(budget.getAantal(), dto.getAantal());
        assertEquals(budget.getNaam(), dto.getNaam());
        assertEquals(budget.getBeginDatum(), dto.getBeginDatum());
        assertEquals(budget.getEindDatum(), dto.getEindDatum());
    }
}
