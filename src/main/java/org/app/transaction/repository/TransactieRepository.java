package org.app.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Repository
public interface TransactieRepository extends JpaRepository<TransactieDBO, UUID>
{
    List<TransactieDBO> findByUser_Id(UUID userID);
    List<TransactieDBO> findByUser_IdAndCreatieDatumBetween(
            UUID userID,
            LocalDate startDatum,
            LocalDate endDatum
    );
    @Query("SELECT COALESCE(SUM(t.aantal), 0) " +
            "FROM TransactieDBO t " +
            "WHERE t.user.id = :userId " +
            "AND t.creatieDatum BETWEEN :begin AND :eind")
    BigDecimal totaalTransactiesTussenPeriodes(
            UUID userId,
            LocalDate begin,
            LocalDate eind
    );
}
