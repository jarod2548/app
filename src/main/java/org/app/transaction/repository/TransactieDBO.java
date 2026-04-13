package org.app.transaction.repository;

import jakarta.persistence.*;
import org.app.Account.infrastructure.UserDBO;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transacties")
public class TransactieDBO
{
    @Id
    @UuidGenerator
    @Column( updatable = false, nullable = false)
    private UUID id;
    @Column(name = "beschrijving")
    private String beschrijving;
    @Column(name = "creatiedatum")
    private LocalDateTime creatieDatum;
    @Column(name = "aantal")
    private BigDecimal aantal;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDBO user;

    protected  TransactieDBO(){}

    public TransactieDBO( String Beschrijving,
                          LocalDateTime CreatieDatum,
                          BigDecimal Aantal,
                          UserDBO userDBO)
    {

        beschrijving = Beschrijving;
        creatieDatum = CreatieDatum;
        aantal = Aantal;
        user = userDBO;
    }

    public UUID getId() {return  id;}
    public void setId(UUID ID){ id = ID;}

    public String getBeschrijving(){return beschrijving;}
    public void setBeschrijving(String Beschrijving) {beschrijving = Beschrijving;}

    public LocalDateTime getCreatieDatum(){return  creatieDatum;}
    public void setCreatieDatum(LocalDateTime CreatieDatum){creatieDatum = CreatieDatum;}

    public BigDecimal getAantal(){return aantal;}
    public void setAantal(BigDecimal Aantal){aantal = Aantal;}

    public UserDBO getUser() {
        return user;
    }

    public void setUser(UserDBO user) {
        this.user = user;
    }
}
