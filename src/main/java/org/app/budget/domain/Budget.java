package org.app.budget.domain;

import org.app.Account.infrastructure.UserDBO;
import org.app.budget.repository.BudgetDBO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Budget {
    private UUID id;
    private BigDecimal aantal;
    private String naam;
    private LocalDate beginDatum;
    private LocalDate eindDatum;
    private UUID userId;

    public Budget(BigDecimal Aantal, String Naam,LocalDate BeginDatum, LocalDate EindDatum)
    {
        aantal = Aantal;
        beginDatum = BeginDatum;
        eindDatum = EindDatum;
        naam = Naam;
    }

    public BudgetDBO naarDBO(UserDBO userDBO){
        BudgetDBO dbo = new BudgetDBO(naam, aantal, beginDatum, eindDatum, userDBO);
        return dbo;
    }

    public Budget(BudgetDBO dbo){
        id = dbo.getId();
        naam = dbo.getNaam();
        aantal = dbo.getAantal();
        beginDatum = dbo.getBeginDatum();
        eindDatum = dbo.getEindDatum();
        userId = dbo.getUser().getId();
    }


    public UUID getId() {
        return id;
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

    public UUID getUserId() {
        return userId;
    }
}
