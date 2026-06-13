package org.app.transaction.listener;

import org.app.budget.service.BudgetService;
import org.app.events.TransactieAangemaaktEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component
public class TransactieEventListener {

    private final BudgetService budgetService;

    public TransactieEventListener(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void onTransactionCreated(TransactieAangemaaktEvent event) {
        budgetService.controleerBudgets(event.transactieID());
    }
}
