package org.transaction.api;

import jakarta.validation.constraints.NotNull;
import org.transaction.domain.Transactie;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactieCreateDTO {
    private Long id;
    @NotNull
    private BigDecimal aantal;
    private String beschrijving;
    @NotNull
    private LocalDateTime datum;

    public TransactieCreateDTO(){}

    public TransactieCreateDTO(BigDecimal aantal, String beschrijving, LocalDateTime datum) {
        this.aantal = aantal;
        this.beschrijving = beschrijving;
        this.datum = datum;
    }

    public TransactieCreateDTO(Transactie transactie)
    {
        this.id = transactie.getId();
        this.aantal = transactie.getAantal();
        this.datum = transactie.getDatum();
        this.beschrijving = transactie.getBeschrijving();
    }

    public Transactie naarTransactie()
    {
        Transactie model = new Transactie(aantal, beschrijving, datum);
        return  model;
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
