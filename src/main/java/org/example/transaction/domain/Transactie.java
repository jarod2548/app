package org.example.transaction.domain;

import org.example.transaction.DBO.TransactieDBO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transactie {
    private Long id;
    private BigDecimal aantal;
    private String beschrijving;
    private LocalDateTime datum;

    public Transactie(BigDecimal aantal, String beschrijving, LocalDateTime datum) {
        this.aantal = aantal;
        this.beschrijving = beschrijving;
        this.datum = datum;
    }

    public Transactie(TransactieDBO dbo)
    {
        this.id = dbo.getId();
        this.aantal = dbo.getAantal();
        this.datum = dbo.getCreatieDatum();
        this.beschrijving = dbo.getBeschrijving();
    }

    public Long getId() {return id;}
    public BigDecimal getAantal() { return aantal; }
    public String getBeschrijving() { return beschrijving; }
    public LocalDateTime getDatum() { return datum; }
}
