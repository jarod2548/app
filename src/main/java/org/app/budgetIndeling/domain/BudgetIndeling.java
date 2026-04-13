package org.app.budgetIndeling.domain;

import org.app.budgetIndeling.repository.BudgetIndelingDBO;

import java.math.BigDecimal;
import java.util.UUID;

public class BudgetIndeling {

    private UUID id;
    private BigDecimal aantal;
    private float percentage;

    public BudgetIndeling(BigDecimal aantal) {
        this.aantal = aantal;
    }

    public BudgetIndeling(BudgetIndelingDBO dbo) {
        this.id = dbo.getId();
        this.aantal = dbo.getAantal();
        this.percentage = dbo.getPercentage();
    }

    public BudgetIndelingDBO naarDBO() {
        return new BudgetIndelingDBO(aantal, percentage);
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