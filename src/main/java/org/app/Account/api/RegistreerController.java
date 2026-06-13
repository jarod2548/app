package org.app.Account.api;


import jakarta.validation.Valid;
import org.app.config.Exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.app.Account.domain.User;
import org.app.Account.service.RegisterService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistreerController {
    private final RegisterService userCreationService;

    public RegistreerController(RegisterService userCreationService) {
        this.userCreationService = userCreationService;
    }

    @PostMapping("/registreer")
    public ResponseEntity<Void> maakUser(@RequestBody @Valid RegistreerDTO dto )
    {
        User model = dto.naarUser();
        userCreationService.maakUser(model);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}