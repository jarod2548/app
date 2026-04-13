package org.app.budget.repository;

import jakarta.persistence.*;
import org.app.Account.infrastructure.UserDBO;

import java.math.BigDecimal;
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
    @Column(name = "BeginDatum")
    private LocalDateTime beginDatum;
    @Column(name = "EindDatum")
    private LocalDateTime eindDatum;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDBO user;

    public BudgetDBO(){};

    public BudgetDBO(BigDecimal Aantal,
                     LocalDateTime BeginDatum,
                     LocalDateTime EindDatum,
                     UserDBO user){
        this.aantal = Aantal;
        this.beginDatum = BeginDatum;
        this.eindDatum = EindDatum;
        this.user = user;
    }

    public void setBeginDatum(LocalDateTime beginDatum) {
        this.beginDatum = beginDatum;
    }

    public void setEindDatum(LocalDateTime eindDatum) {
        this.eindDatum = eindDatum;
    }

    public void setAantal(BigDecimal aantal) {
        this.aantal = aantal;
    }

    public LocalDateTime getEindDatum() {
        return eindDatum;
    }

    public LocalDateTime getBeginDatum() {
        return beginDatum;
    }

    public BigDecimal getAantal() {
        return aantal;
    }
}
