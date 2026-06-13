package org.app.budget.api;

import org.app.budget.domain.Budget;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class BudgetResponseDTO {
    private UUID id;
    private String naam;
    private BigDecimal aantal;
    private LocalDate beginDatum;
    private LocalDate eindDatum;

    public BudgetResponseDTO(Budget model){
        id = model.getId();
        aantal = model.getAantal();
        beginDatum = model.getBeginDatum();
        eindDatum = model.getEindDatum();
        naam = model.getNaam();
    }

    public BigDecimal getAantal() {
        return aantal;
    }

    public LocalDate getBeginDatum() {
        return beginDatum;
    }

    public LocalDate getEindDatum() {
        return eindDatum;
    }

    public String getNaam() {
        return naam;
    }

    public UUID getId() {
        return id;
    }
}
