package org.transaction.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacties")
public class TransactieDBO
{
    @Id
    private Long id;
    @Column(name = "beschrijving")
    private String beschrijving;
    @Column(name = "creatiedatum")
    private LocalDateTime creatieDatum;
    @Column(name = "aantal")
    private BigDecimal aantal;

    protected  TransactieDBO(){}

    public TransactieDBO(long ID, String Beschrijving, LocalDateTime CreatieDatum, BigDecimal Aantal)
    {
        id = ID;
        beschrijving = Beschrijving;
        creatieDatum = CreatieDatum;
        aantal = Aantal;
    }

    public Long getId() {return  id;}
    public void setId(Long ID){ id = ID;}

    public String getBeschrijving(){return beschrijving;}
    public void setBeschrijving(String Beschrijving) {beschrijving = Beschrijving;}

    public LocalDateTime getCreatieDatum(){return  creatieDatum;}
    public void setCreatieDatum(LocalDateTime CreatieDatum){creatieDatum = CreatieDatum;}

    public BigDecimal getAantal(){return aantal;}
    public void setAantal(BigDecimal Aantal){aantal = Aantal;}
}
