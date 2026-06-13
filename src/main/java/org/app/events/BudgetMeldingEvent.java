package org.app.events;

import org.app.budget.domain.BudgetMelding;

import java.util.UUID;

public record BudgetMeldingEvent(UUID userId, BudgetMelding melding) {
}
