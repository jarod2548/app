package org.app.budget.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class BudgetMelding {
    private String budgetNaam;
    private UUID budgetID;
    private BigDecimal overschredenAantal;

    public BudgetMelding(String BudgetNaam, UUID BudgetID, BigDecimal OverschredenAantal){
        budgetNaam = BudgetNaam;
        budgetID = BudgetID;
        overschredenAantal = OverschredenAantal;
    }

    public UUID getBudgetID() {
        return budgetID;
    }

    public String getBudgetNaam() {
        return budgetNaam;
    }

    public BigDecimal getOverschredenAantal() {
        return overschredenAantal;
    }

    public void setBudgetID(UUID budgetID) {
        this.budgetID = budgetID;
    }

    public void setBudgetNaam(String budgetNaam) {
        this.budgetNaam = budgetNaam;
    }

    public void setOverschredenAantal(BigDecimal overschredenAantal) {
        this.overschredenAantal = overschredenAantal;
    }
}
