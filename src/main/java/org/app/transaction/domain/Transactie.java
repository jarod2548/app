package org.app.transaction.domain;

import org.app.Account.infrastructure.UserDBO;
import org.app.categorie.repository.CategorieDBO;
import org.app.transaction.repository.TransactieDBO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transactie {
    private UUID id;
    private BigDecimal aantal;
    private String beschrijving;
    private LocalDateTime datum;
    private UUID userID;
    private UUID categorieID;

    public Transactie(BigDecimal Aantal, String Beschrijving, LocalDateTime Datum){
        this.aantal = Aantal;
        this.beschrijving = Beschrijving;
        this.datum = Datum;
    }

    public Transactie(TransactieDBO dbo)
    {
        this.id = dbo.getId();
        this.aantal = dbo.getAantal();
        this.datum = dbo.getCreatieDatum();
        this.beschrijving = dbo.getBeschrijving();
        this.categorieID = dbo.getCategorie() != null
                        ? dbo.getCategorie().getId()
                        : null;
    }

    public TransactieDBO naarDBO(UserDBO user, CategorieDBO categorieDBO){
        TransactieDBO dbo = new TransactieDBO( beschrijving,
                                               datum,
                                               aantal,
                                               user,
                                               categorieDBO);
        return  dbo;
    }

    public UUID getId() {return id;}
    public BigDecimal getAantal() { return aantal; }
    public String getBeschrijving() { return beschrijving; }
    public LocalDateTime getDatum() { return datum; }

    public UUID getCategorieID() {
        return categorieID;
    }

}
