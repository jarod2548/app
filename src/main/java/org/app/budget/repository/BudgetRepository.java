package org.app.budget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetDBO, UUID> {
    List<BudgetDBO> findByUser_Id(UUID userID);
    @Query(" SELECT b " +
            "FROM BudgetDBO b " +
            "WHERE b.user.id = :userId " +
            "AND b.beginDatum <= :creatieDatum " +
            "AND b.eindDatum >= :creatieDatum")
    List<BudgetDBO> findBudgetsVanTransactie(
            UUID userId,
            LocalDate creatieDatum
    );
}
