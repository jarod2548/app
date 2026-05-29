package org.app.transaction.api;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.app.transaction.domain.Transactie;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactieCreateDTO {
    private Long id;
    @NotNull(message = "Aantal moet ingevuld zijn")
    @DecimalMin(value = "0.01", message = "Aantal moet groter zijn dan 0")
    private BigDecimal aantal;
    private String beschrijving;
    @NotNull(message = "Datum moet ingevuld zijn")
    private LocalDateTime datum;

    //Voor de creatie van Json bestand door Springboot
    public TransactieCreateDTO(){}


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
