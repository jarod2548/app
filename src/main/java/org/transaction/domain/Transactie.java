package org.transaction.domain;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import org.transaction.api.TransactieCreateDTO;
import org.transaction.repository.TransactieDBO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transactie {
    private Long id;
    @NotNull(message = "Aantal is verplicht")
    @DecimalMin(value = "0.01", message = "Aantal moet groter zijn dan 0")
    private BigDecimal aantal;
    private String beschrijving;
    @NotNull(message = "Datum is verplicht")
    private LocalDateTime datum;

    public Transactie(BigDecimal Aantal, String Beschrijving, LocalDateTime Datum){
        this.aantal = Aantal;
        this.beschrijving = Beschrijving;
        this.datum = Datum;
    }

    public Transactie(TransactieCreateDTO dto) {
        this.aantal = dto.getAantal();
        this.beschrijving = dto.getBeschrijving();
        this.datum = dto.getDatum();
    }

    public Transactie(TransactieDBO dbo)
    {
        this.id = dbo.getId();
        this.aantal = dbo.getAantal();
        this.datum = dbo.getCreatieDatum();
        this.beschrijving = dbo.getBeschrijving();
    }

    public TransactieDBO naarDBO(){
        TransactieDBO dbo = new TransactieDBO(id, beschrijving, datum, aantal);
        return  dbo;
    }

    public Long getId() {return id;}
    public BigDecimal getAantal() { return aantal; }
    public String getBeschrijving() { return beschrijving; }
    public LocalDateTime getDatum() { return datum; }
}
