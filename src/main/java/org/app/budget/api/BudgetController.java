package org.app.budget.api;

import jakarta.validation.Valid;
import org.app.budget.domain.Budget;
import org.app.budget.service.BudgetService;
import org.app.config.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BudgetController {

    private final BudgetService service;

    public BudgetController(BudgetService Service){
        service = Service;
    }

    @PostMapping("/user/maakBudget")
    public ResponseEntity<BudgetDTO> maakBudget(@Valid
                                                @RequestBody
                                                BudgetCreateDTO dto,
                                                @AuthenticationPrincipal UserPrincipal user){
        Budget model = dto.naarBudget();
        Budget responseModel = service.maakBudget(model, user.getId());
        BudgetDTO response = new BudgetDTO(responseModel);
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
