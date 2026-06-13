package org.app.budget.domain;

import org.app.budgetIndeling.domain.BudgetIndeling;
import org.app.budgetIndeling.domain.BudgetIndelingOverview;

import java.math.BigDecimal;
import java.util.List;

public class BudgetOverview {
    private Budget budget;
    private List<BudgetIndelingOverview> indelingen;
    private BigDecimal totaalUitgave;

    public BudgetOverview(Budget Budget,
                          List<BudgetIndelingOverview> Indelingen,
                          BigDecimal TotaalUitgave){
        budget = Budget;
        indelingen = Indelingen;
        totaalUitgave = TotaalUitgave;
    }

    public Budget getBudget() {
        return budget;
    }

    public BigDecimal getTotaalUitgave() {
        return totaalUitgave;
    }

    public List<BudgetIndelingOverview> getIndelingen() {
        return indelingen;
    }
}
