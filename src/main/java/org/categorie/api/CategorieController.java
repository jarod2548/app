package org.categorie.api;

import org.categorie.domain.Categorie;
import org.categorie.service.CategorieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
public class CategorieController
{
    private final CategorieService service;

    public CategorieController(CategorieService Service)
    {
        service = Service;
    }

    @PostMapping("/api/maakCategorie")
    public void maakCategorie(CategorieDTO dto)
    {
        Categorie model = dto.converteerNaarModel();
        boolean success = service.maakCategorie(model);
    }

    @GetMapping("/api/leesCategorien")
    public List<CategorieDTO> leesCategories()
    {
        List<CategorieDTO> dtos = new ArrayList<>();
        List<Categorie> models = service.leesCategorien();
        for (Categorie model : models){
            dtos.add(new CategorieDTO(model));
        }
        return dtos;
    }


}
