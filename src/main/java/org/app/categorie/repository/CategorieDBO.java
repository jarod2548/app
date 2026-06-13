package org.app.categorie.repository;

import jakarta.persistence.*;
import org.app.Account.infrastructure.UserDBO;

import java.util.UUID;

@Entity
@Table(name = "Categorie")
public class CategorieDBO {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "Isbelangrijk")
    private boolean isBelangrijk;

    @Column(name = "Naam")
    private String naam;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDBO user;

    public CategorieDBO() {}

    public CategorieDBO(boolean isBelangrijk, String naam, UserDBO userDBO) {
        this.isBelangrijk = isBelangrijk;
        this.naam = naam;
        this.user = userDBO;
    }

    public UUID getId() {
        return id;
    }

    public boolean isBelangrijk() {
        return isBelangrijk;
    }

    public String getNaam() {
        return naam;
    }

    public void setBelangrijk(boolean belangrijk) {
        isBelangrijk = belangrijk;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}
