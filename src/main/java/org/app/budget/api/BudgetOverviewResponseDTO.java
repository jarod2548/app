package org.app.budget.api;

import org.app.budget.domain.BudgetOverview;
import org.app.budgetIndeling.api.BudgetIndelingResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public class BudgetOverviewResponseDTO {
    private BudgetResponseDTO budgetDTO;
    private List<BudgetIndelingResponseDTO> indelingen;
    private BigDecimal totaalUitgave;

    public BudgetOverviewResponseDTO(BudgetOverview budgetOverview)
    {
        budgetDTO = new BudgetResponseDTO(budgetOverview.getBudget());
        indelingen = budgetOverview.getIndelingen()
                .stream()
                .map(BudgetIndelingResponseDTO::new)
                .toList();
        totaalUitgave = budgetOverview.getTotaalUitgave();
    }

    public BigDecimal getTotaalUitgave() {
        return totaalUitgave;
    }

    public BudgetResponseDTO getBudgetDTO() {
        return budgetDTO;
    }

    public List<BudgetIndelingResponseDTO> getIndelingen() {
        return indelingen;
    }
}
