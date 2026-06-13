package org.app.budgetIndeling.domain;

import org.app.budgetIndeling.repository.BudgetIndelingDBO;

import java.math.BigDecimal;
import java.util.UUID;

public class BudgetIndelingOverview {
    private final String categorieNaam;
    private final BigDecimal uitgave;
    private BigDecimal percentage;
    private final UUID categorieID;

    public BudgetIndelingOverview(String CategorieNaam,
                                  BigDecimal Uitgave,
                                  BigDecimal Percentage,
                                  UUID categorieID){
        categorieNaam = CategorieNaam;
        uitgave = Uitgave;
        percentage = Percentage;
        this.categorieID = categorieID;
    }

    public BudgetIndelingOverview(BudgetIndelingDBO dbo) {
        this.categorieID = dbo.getId();
        this.uitgave = dbo.getAantal();
        this.categorieNaam = dbo.getCategorie().getNaam();
    }

    public BigDecimal getUitgave() {
        return uitgave;
    }

    public String getCategorieNaam() {
        return categorieNaam;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public UUID getCategorieID() {
        return categorieID;
    }
}
