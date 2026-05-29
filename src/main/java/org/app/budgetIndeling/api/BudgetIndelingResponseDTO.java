package org.app.budgetIndeling.api;


import org.app.budgetIndeling.domain.BudgetIndeling;
import org.app.budgetIndeling.domain.BudgetIndelingOverview;

import java.math.BigDecimal;
import java.util.UUID;

public class BudgetIndelingResponseDTO {

    private UUID id;
    private BigDecimal aantal;
    private BigDecimal percentage;
    private String categorieNaam;

    public BudgetIndelingResponseDTO(BudgetIndelingOverview model) {
        this.id = model.getId();
        this.aantal = model.getUitgave();
        this.percentage = model.getPercentage();
        this.categorieNaam = model.getCategorieNaam();
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getAantal() {
        return aantal;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public String getCategorieNaam() {
        return categorieNaam;
    }
}
