package org.app.budget.api;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.app.budget.domain.Budget;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BudgetCreateDTO {
    @DecimalMin(value = "0.01", message = "Aantal moet meer dan 0 zijn")
    @NotNull
    private BigDecimal aantal;
    @NotNull
    @Size(min = 1, message = "Naam moet 1 karakter hebben")
    private String naam;
    private LocalDateTime beginDatum;
    private LocalDateTime eindDatum;

    //Voor de creatie van Json bestand door Springboot
    public BudgetCreateDTO(){}

    public Budget naarBudget(){
        return new Budget(aantal,naam, beginDatum,eindDatum);
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

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }
}
