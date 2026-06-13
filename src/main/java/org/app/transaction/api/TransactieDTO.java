package org.app.transaction.api;

import org.app.transaction.domain.Transactie;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class TransactieDTO {
    private UUID id;
    private BigDecimal aantal;
    private String beschrijving;
    private LocalDate datum;
    private String categorieNaam;


    public TransactieDTO(){}


    public TransactieDTO(Transactie transactie)
    {
        this.id = transactie.getId();
        this.aantal = transactie.getAantal();
        this.datum = transactie.getDatum();
        this.beschrijving = transactie.getBeschrijving();
        this.categorieNaam = transactie.getCategorieNaam();
    }

    public UUID getId() {return id;}
    public BigDecimal getAantal() { return aantal; }
    public String getBeschrijving() { return beschrijving; }
    public LocalDate getDatum() { return datum; }

    public String getCategorieNaam() {
        return categorieNaam;
    }

}
