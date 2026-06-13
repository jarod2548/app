package org.app.budget.repository;

import jakarta.persistence.*;
import org.app.Account.infrastructure.UserDBO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "Budget")
public class BudgetDBO {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "Aantal")
    private BigDecimal aantal;
    @Column(name = "Begindatum")
    private LocalDate beginDatum;
    @Column(name = "Einddatum")
    private LocalDate eindDatum;
    @Column(name = "Naam")
    private String naam;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDBO user;

    public BudgetDBO(){};

    public BudgetDBO(String Naam,
                     BigDecimal Aantal,
                     LocalDate BeginDatum,
                     LocalDate EindDatum,
                     UserDBO user){
        this.naam = Naam;
        this.aantal = Aantal;
        this.beginDatum = BeginDatum;
        this.eindDatum = EindDatum;
        this.user = user;
    }

    public void setBeginDatum(LocalDate beginDatum) {
        this.beginDatum = beginDatum;
    }

    public void setEindDatum(LocalDate eindDatum) {
        this.eindDatum = eindDatum;
    }

    public void setAantal(BigDecimal aantal) {
        this.aantal = aantal;
    }

    public LocalDate getEindDatum() {
        return eindDatum;
    }

    public LocalDate getBeginDatum() {
        return beginDatum;
    }

    public BigDecimal getAantal() {
        return aantal;
    }

    public UserDBO getUser() {
        return user;
    }

    public void setUser(UserDBO user) {
        this.user = user;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
