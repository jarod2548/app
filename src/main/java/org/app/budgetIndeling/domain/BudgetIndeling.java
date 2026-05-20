package org.app.budgetIndeling.domain;

import org.app.budget.repository.BudgetDBO;
import org.app.budgetIndeling.repository.BudgetIndelingDBO;
import org.app.categorie.repository.CategorieDBO;

import java.math.BigDecimal;
import java.util.UUID;

public class BudgetIndeling {

    private UUID id;
    private BigDecimal aantal;
    private float percentage;
    private String categorieNaam;
    private UUID categorieID;
    private UUID budgetID;

    public BudgetIndeling(BigDecimal aantal, UUID CategorieID, UUID BudgetID) {
        this.aantal = aantal;
        this.budgetID = BudgetID;
        this.categorieID = CategorieID;
    }

    public BudgetIndeling(BudgetIndelingDBO dbo) {
        this.id = dbo.getId();
        this.aantal = dbo.getAantal();
        this.categorieNaam = dbo.getCategorie().getNaam();
    }

    public BudgetIndelingDBO naarDBO(CategorieDBO categorie, BudgetDBO budget) {
        return new BudgetIndelingDBO(aantal, budget, categorie);
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

    public UUID getBudgetID() {
        return budgetID;
    }

    public UUID getCategorieID() {
        return categorieID;
    }

    public String getCategorieNaam() {
        return categorieNaam;
    }
}