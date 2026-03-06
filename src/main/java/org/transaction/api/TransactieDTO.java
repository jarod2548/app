package org.transaction.api;

import org.transaction.domain.Transactie;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactieDTO {
    private Long id;
    private BigDecimal aantal;
    private String beschrijving;
    private LocalDateTime datum;

    public TransactieDTO(){}

    public TransactieDTO(BigDecimal aantal, String beschrijving, LocalDateTime datum) {
        this.aantal = aantal;
        this.beschrijving = beschrijving;
        this.datum = datum;
    }

    public TransactieDTO(Transactie transactie)
    {
        this.id = transactie.getId();
        this.aantal = transactie.getAantal();
        this.datum = transactie.getDatum();
        this.beschrijving = transactie.getBeschrijving();
    }

    public Long getId() {return id;}
    public BigDecimal getAantal() { return aantal; }
    public String getBeschrijving() { return beschrijving; }
    public LocalDateTime getDatum() { return datum; }

    public void setId(Long ID) {id = ID;}

    public void setAantal(BigDecimal aantal) {
        this.aantal = aantal;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }
}
