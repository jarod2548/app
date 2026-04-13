package org.app.transaction.api;

import jakarta.validation.constraints.NotNull;
import org.app.transaction.domain.Transactie;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactieDTO {
    private UUID id;
    @NotNull
    private BigDecimal aantal;
    private String beschrijving;
    @NotNull
    private LocalDateTime datum;


    public TransactieDTO(){}


    public TransactieDTO(Transactie transactie)
    {
        this.id = transactie.getId();
        this.aantal = transactie.getAantal();
        this.datum = transactie.getDatum();
        this.beschrijving = transactie.getBeschrijving();
    }

    public UUID getId() {return id;}
    public BigDecimal getAantal() { return aantal; }
    public String getBeschrijving() { return beschrijving; }
    public LocalDateTime getDatum() { return datum; }


}
