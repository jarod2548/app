package org.app.budget.listener;

import org.app.SSE.SSEService;
import org.app.events.BudgetMeldingEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BudgetEventListener {

    private final SSEService sseService;

    public BudgetEventListener( SSEService sseService) {
        this.sseService = sseService;
    }

    @EventListener
    public void onBudgetMelding(BudgetMeldingEvent event){
        sseService.sendToUser(
                event.userId(),
                event.melding(),
                "budget-melding"
        );
    }
}
