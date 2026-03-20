package org.budget.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Budget {
    private BigDecimal aantal;
    private LocalDateTime beginDatum;
    private LocalDateTime eindDatum;

    public Budget(BigDecimal Aantal, LocalDateTime BeginDatum, LocalDateTime EindDatum)
    {
        aantal = Aantal;
        beginDatum = BeginDatum;
        eindDatum = EindDatum;
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
