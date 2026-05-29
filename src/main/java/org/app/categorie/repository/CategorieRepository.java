package org.app.categorie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategorieRepository extends JpaRepository<CategorieDBO, UUID> {
    List<CategorieDBO> findByUser_Id(UUID userID);
}