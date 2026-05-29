package org.app.budgetIndeling.api;


import org.app.budgetIndeling.domain.BudgetIndeling;
import org.app.budgetIndeling.domain.BudgetIndelingOverview;
import org.app.budgetIndeling.service.BudgetIndelingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class BudgetIndelingController {

    private final BudgetIndelingService service;

    public BudgetIndelingController(BudgetIndelingService service) {
        this.service = service;
    }

    @PostMapping("/user/budgetIndeling")
    public ResponseEntity<BudgetIndelingResponseDTO> maakBudgetIndeling(BudgetIndelingCreateDTO dto) {
        BudgetIndeling model = dto.naarModel();
        BudgetIndelingOverview responseModel = service.maakBudgetIndeling(model);
        return ResponseEntity.ok(new BudgetIndelingResponseDTO(responseModel));
    }
}
