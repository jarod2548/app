package org.app.categorie.api;

import org.app.categorie.domain.Categorie;
import org.app.categorie.service.CategorieService;
import org.app.config.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class CategorieController {

    private final CategorieService service;

    public CategorieController(CategorieService service) {
        this.service = service;
    }

    @PostMapping("/user/categorie")
    public ResponseEntity<Void> maakCategorie(@RequestBody
                                              CategorieCreateDTO dto,
                                              @AuthenticationPrincipal UserPrincipal user) {
        Categorie model = dto.naarModel();
        service.maakCategorie(model, user.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/categorie")
    public ResponseEntity<List<CategorieResponseDTO>> leesCategorien(@AuthenticationPrincipal UserPrincipal user){
        List<Categorie> categories = service.leesCategories(user.getId());

        return ResponseEntity.ok(categories.stream()
                .map(CategorieResponseDTO::new)
                .toList());
    }
}