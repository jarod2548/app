package org.app.categorie.api;

import org.app.categorie.domain.Categorie;
import org.app.categorie.service.CategorieService;
import org.app.config.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategorieController {

    private final CategorieService service;

    public CategorieController(CategorieService service) {
        this.service = service;
    }

    @PostMapping("/user/maakCategorie")
    public CategorieResponseDTO maakCategorie(CategorieCreateDTO dto,
                                              @AuthenticationPrincipal UserPrincipal user) {
        Categorie model = dto.naarModel();
        Categorie responseModel = service.maakCategorie(model, user.getId());
        return new CategorieResponseDTO(responseModel);
    }
}