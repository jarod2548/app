package org.app.budgetIndeling.api;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.app.budgetIndeling.domain.BudgetIndeling;

import java.math.BigDecimal;
import java.util.UUID;

public class BudgetIndelingCreateDTO {

    private UUID id;
    @NotNull(message = "Aantal moet bestaan")
    @DecimalMin(value = "0.01", message = "Aantal moet meer dan 0 zijn")
    private BigDecimal aantal;

    public BudgetIndeling naarModel() {
        return new BudgetIndeling(aantal);
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getAantal() {
        return aantal;
    }
}
