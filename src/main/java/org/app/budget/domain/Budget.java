package org.app.budget.domain;

import org.app.Account.infrastructure.UserDBO;
import org.app.budget.repository.BudgetDBO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Budget {
    private UUID id;
    private BigDecimal aantal;
    private LocalDateTime beginDatum;
    private LocalDateTime eindDatum;

    public Budget(BigDecimal Aantal, LocalDateTime BeginDatum, LocalDateTime EindDatum)
    {
        aantal = Aantal;
        beginDatum = BeginDatum;
        eindDatum = EindDatum;
    }

    public BudgetDBO naarDBO(UserDBO userDBO){
        BudgetDBO dbo = new BudgetDBO(aantal, beginDatum, eindDatum, userDBO);
        return dbo;
    }

    public Budget(BudgetDBO dbo){
        aantal = dbo.getAantal();
        beginDatum = dbo.getBeginDatum();
        eindDatum = dbo.getEindDatum();
    }


    public UUID getId() {
        return id;
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
}
