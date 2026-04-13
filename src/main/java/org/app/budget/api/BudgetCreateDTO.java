package org.app.budget.api;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.app.budget.domain.Budget;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BudgetCreateDTO {
    @DecimalMin(value = "0.01", message = "Aantal moet meer dan 0 zijn")
    @NotNull
    private BigDecimal aantal;
    private LocalDateTime beginDatum;
    private LocalDateTime eindDatum;

    public BudgetCreateDTO(){

    }

    public Budget naarBudget(){
        Budget model = new Budget(aantal,beginDatum,eindDatum);
        return model;
    }

    public LocalDateTime getEindDatum() {
        return eindDatum;
    }

    public LocalDateTime getBeginDatum() {
        return beginDatum;
    }

    public BigDecimal getAantal() {
        return aantal;
    }

    public void setEindDatum(LocalDateTime eindDatum) {
        this.eindDatum = eindDatum;
    }

    public void setBeginDatum(LocalDateTime beginDatum) {
        this.beginDatum = beginDatum;
    }

    public void setAantal(BigDecimal aantal) {
        this.aantal = aantal;
    }
}
