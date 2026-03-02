package org.example.transaction.repository;

import org.example.transaction.DBO.TransactieDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactieRepository extends JpaRepository<TransactieDBO, Long>
{


}
