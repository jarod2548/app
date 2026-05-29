package org.app.budgetIndeling.domain;

import org.app.budget.repository.BudgetDBO;
import org.app.budgetIndeling.repository.BudgetIndelingDBO;
import org.app.categorie.repository.CategorieDBO;
import org.app.transaction.domain.Transactie;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class BudgetIndeling {

    private UUID id;
    private BigDecimal aantalSuggestie;
    private String categorieNaam;
    private UUID categorieID;
    private UUID budgetID;

    public BudgetIndeling(BigDecimal aantal, UUID CategorieID, UUID BudgetID) {
        this.aantalSuggestie = aantal;
        this.budgetID = BudgetID;
        this.categorieID = CategorieID;
    }

    public BudgetIndeling(BudgetIndelingDBO dbo) {
        this.id = dbo.getId();
        this.aantalSuggestie = dbo.getAantal();
        this.categorieNaam = dbo.getCategorie().getNaam();
        this.categorieID = dbo.getCategorie().getId();
    }


    public BudgetIndelingDBO naarDBO(CategorieDBO categorie, BudgetDBO budget) {
        return new BudgetIndelingDBO(aantalSuggestie, budget, categorie);
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getAantalSuggestie() {
        return aantalSuggestie;
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