package org.app.transaction.api;

import jakarta.validation.Valid;
import org.app.config.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.app.transaction.domain.Transactie;
import org.app.transaction.service.TransactieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
public class TransactieController {
    private final TransactieService service;

    public TransactieController(TransactieService Service)
    {
        service = Service;
    }

    @PostMapping("/user/transactie")
    public ResponseEntity<Void> slaTransactieOp(
            @RequestBody
            @Valid
            TransactieCreateDTO dto,
            @AuthenticationPrincipal UserPrincipal user)
    {
            Transactie model = dto.naarTransactie();
            service.slaTransactieOp(model, user.getId());
            return  ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @GetMapping("/user/transactie")
    public List<TransactieDTO> leesTransacties() {
        List<TransactieDTO> responses = new ArrayList<>();
        List<Transactie> transacties = service.leesTransacties();
        for (Transactie t : transacties) {
            responses.add(new TransactieDTO(t));
        }
        return responses;
    }

}
