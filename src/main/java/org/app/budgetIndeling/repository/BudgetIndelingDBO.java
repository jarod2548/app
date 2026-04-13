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

    @Column(name = "Percentage")
    private float percentage;

    @ManyToOne
    @JoinColumn(name = "budget_id", nullable = false)
    private BudgetDBO budget;

    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private CategorieDBO categorie;

    public BudgetIndelingDBO() {}

    public BudgetIndelingDBO(BigDecimal aantal, float percentage) {
        this.aantal = aantal;
        this.percentage = percentage;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getAantal() {
        return aantal;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setAantal(BigDecimal aantal) {
        this.aantal = aantal;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}