package org.app.budgetIndeling.api;


import org.app.budgetIndeling.domain.BudgetIndeling;
import org.app.budgetIndeling.service.BudgetIndelingService;
import org.springframework.stereotype.Controller;

@Controller
public class BudgetIndelingController {

    private final BudgetIndelingService service;

    public BudgetIndelingController(BudgetIndelingService service) {
        this.service = service;
    }

    public BudgetIndelingDTO maakBudgetIndeling(BudgetIndelingCreateDTO dto) {
        BudgetIndeling model = dto.naarModel();
        BudgetIndeling responseModel = service.maakBudgetIndeling(model);
        return new BudgetIndelingDTO(responseModel);
    }
}
