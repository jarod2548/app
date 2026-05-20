package org.app.budgetIndeling.api;


import org.app.budgetIndeling.domain.BudgetIndeling;
import org.app.categorie.api.CategorieResponseDTO;

import java.math.BigDecimal;
import java.util.UUID;

public class BudgetIndelingResponseDTO {

    private UUID id;
    private BigDecimal aantal;
    private float percentage;
    private String categorieNaam;

    public BudgetIndelingResponseDTO(BudgetIndeling model) {
        this.id = model.getId();
        this.aantal = model.getAantal();
        this.percentage = model.getPercentage();
        this.categorieNaam = model.getCategorieNaam();
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getAantal() {
        return aantal;
    }

    public float getPercentage() {
        return percentage;
    }

    public String getCategorieNaam() {
        return categorieNaam;
    }
}
