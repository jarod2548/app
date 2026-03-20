package org.transaction.api;

import jakarta.validation.constraints.NotNull;
import org.transaction.domain.Transactie;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactieDTO {
    private Long id;
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

    public Long getId() {return id;}
    public BigDecimal getAantal() { return aantal; }
    public String getBeschrijving() { return beschrijving; }
    public LocalDateTime getDatum() { return datum; }


}
