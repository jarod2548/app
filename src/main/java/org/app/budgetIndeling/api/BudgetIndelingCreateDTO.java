package org.app.budgetIndeling.api;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.app.budgetIndeling.domain.BudgetIndeling;

import java.math.BigDecimal;
import java.util.UUID;

public class BudgetIndelingCreateDTO {

    @NotNull(message = "Aantal moet bestaan")
    @DecimalMin(value = "0.01", message = "Aantal moet meer dan 0 zijn")
    private BigDecimal aantal;
    @NotNull
    private UUID categorieID;
    @NotNull
    private UUID budgetID;

    public BudgetIndeling naarModel() {
        return new BudgetIndeling(aantal, categorieID, budgetID);
    }


    public BigDecimal getAantal() {
        return aantal;
    }

    public void setAantal(BigDecimal aantal) {
        this.aantal = aantal;
    }

    public void setCategorieID(UUID categorieID) {
        this.categorieID = categorieID;
    }

    public void setBudgetID(UUID budgetID) {
        this.budgetID = budgetID;
    }
}
