package org.app.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Repository
public interface TransactieRepository extends JpaRepository<TransactieDBO, UUID>
{
    List<TransactieDBO> findByUser_Id(UUID userID);
    List<TransactieDBO> findByUser_IdAndCreatieDatumBetween(
            UUID userID,
            LocalDateTime startDatum,
            LocalDateTime endDatum
    );
}
