package org.app.budgetIndeling.api;


import org.app.budgetIndeling.domain.BudgetIndeling;

import java.math.BigDecimal;
import java.util.UUID;

public class BudgetIndelingDTO {

    private UUID id;
    private BigDecimal aantal;
    private float percentage;

    public BudgetIndelingDTO(BudgetIndeling model) {
        this.id = model.getId();
        this.aantal = model.getAantal();
        this.percentage = model.getPercentage();
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getAantal() {
        return aantal;
    }

    public float getPercentage() {
        return percentage;
    }
}
