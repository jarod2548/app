package org.app.budget.api;

import org.app.budget.domain.Budget;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class BudgetDTO {
    private UUID id;
    private String naam;
    private BigDecimal aantal;
    private LocalDateTime beginDatum;
    private LocalDateTime eindDatum;

    public BudgetDTO(Budget model){
        aantal = model.getAantal();
        beginDatum = model.getBeginDatum();
        eindDatum = model.getEindDatum();
        naam = model.getNaam();
    }

    public BigDecimal getAantal() {
        return aantal;
    }

    public LocalDateTime getBeginDatum() {
        return beginDatum;
    }

    public LocalDateTime getEindDatum() {
        return eindDatum;
    }

    public String getNaam() {
        return naam;
    }

    public UUID getId() {
        return id;
    }
}
