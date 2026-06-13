package org.example.transactie.api;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.app.transaction.api.TransactieCreateDTO;
import org.app.transaction.api.TransactieDTO;
import org.app.transaction.domain.Transactie;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactieModelTests {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void transactieDTO_shouldFailValidation_whenAantalIsNull() {
        TransactieCreateDTO dto = new TransactieCreateDTO();
        dto.setAantal(null);          // @NotNull
        dto.setDatum(LocalDate.now());

        Set<ConstraintViolation<TransactieCreateDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("aantal")));
    }

    @Test
    void transactieDTO_shouldFailValidation_whenDatumIsNull() {
        TransactieCreateDTO dto = new TransactieCreateDTO();
        dto.setAantal(BigDecimal.TEN);
        dto.setDatum(null);           // @NotNull

        Set<ConstraintViolation<TransactieCreateDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("datum")));
    }

    @Test
    void transactieDTO_shouldPassValidation_whenAllFieldsSet() {
        TransactieCreateDTO dto = new TransactieCreateDTO();
        dto.setAantal(BigDecimal.TEN);
        dto.setDatum(LocalDate.now());

        Set<ConstraintViolation<TransactieCreateDTO>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void transactieDTO_shouldMapFromTransactieCorrectly() {
        Transactie transactie = new Transactie(new BigDecimal("100"),"Test transactie",
                LocalDate.of(2026, 3, 19));

        TransactieDTO dto = new TransactieDTO(transactie);

        assertEquals(transactie.getId(), dto.getId());
        assertEquals(transactie.getAantal(), dto.getAantal());
        assertEquals(transactie.getDatum(), dto.getDatum());
        assertEquals(transactie.getBeschrijving(), dto.getBeschrijving());
    }
}
