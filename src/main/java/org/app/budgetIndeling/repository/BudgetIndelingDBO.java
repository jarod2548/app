package org.app.budgetIndeling.repository;

import jakarta.persistence.*;
import org.app.budget.repository.BudgetDBO;
import org.app.categorie.repository.CategorieDBO;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "BudgetIndeling")
public class BudgetIndelingDBO {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "Aantal")
    private BigDecimal aantal;


    @ManyToOne
    @JoinColumn(name = "budget_id", nullable = false)
    private BudgetDBO budget;

    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private CategorieDBO categorie;

    public BudgetIndelingDBO() {}

    public BudgetIndelingDBO(BigDecimal aantal, BudgetDBO Budget, CategorieDBO Categorie) {
        this.aantal = aantal;
        this.budget = Budget;
        this.categorie = Categorie;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getAantal() {
        return aantal;
    }

    public void setAantal(BigDecimal aantal) {
        this.aantal = aantal;
    }

    public BudgetDBO getBudget() {
        return budget;
    }

    public CategorieDBO getCategorie() {
        return categorie;
    }

    public void setBudget(BudgetDBO budget) {
        this.budget = budget;
    }

    public void setCategorie(CategorieDBO categorie) {
        this.categorie = categorie;
    }
}