package org.app.budget.api;

import jakarta.validation.Valid;
import org.app.budget.domain.Budget;
import org.app.budget.domain.BudgetOverview;
import org.app.budget.service.BudgetOverviewService;
import org.app.budget.service.BudgetService;
import org.app.config.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Controller
public class BudgetController {

    private final BudgetService budgetService;
    private final BudgetOverviewService budgetOverviewService;

    public BudgetController(BudgetService BudgetService, BudgetOverviewService budgetOverviewService){
        budgetService = BudgetService;
        this.budgetOverviewService = budgetOverviewService;
    }

    @PostMapping("/user/budget")
    public ResponseEntity<BudgetResponseDTO> maakBudget(@Valid
                                                @RequestBody
                                                BudgetCreateDTO dto,
                                                        @AuthenticationPrincipal UserPrincipal user){
        Budget model = dto.naarBudget();
        Budget responseModel = budgetService.maakBudget(model, user.getId());
        BudgetResponseDTO response = new BudgetResponseDTO(responseModel);
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/user/budgets")
    public ResponseEntity<List<BudgetResponseDTO>> leesBudgets(@AuthenticationPrincipal UserPrincipal user){
        List<Budget> budgetten = budgetService.leesBudgeten(user.getId());
        return ResponseEntity.ok(budgetten
                                .stream()
                                .map(BudgetResponseDTO::new)
                                .toList());
    }

    @GetMapping("/user/budget/{budgetID}")
    public ResponseEntity<BudgetOverviewResponseDTO> leesBudget(@PathVariable UUID budgetID,
                                                                @AuthenticationPrincipal UserPrincipal user){
        BudgetOverview budgetOverview = budgetOverviewService.leesBudgetOverview(budgetID, user.getId());

        return ResponseEntity.ok(new BudgetOverviewResponseDTO(budgetOverview));
    }
}
