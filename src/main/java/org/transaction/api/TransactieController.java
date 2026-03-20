package org.transaction.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.transaction.domain.Transactie;
import org.transaction.service.TransactieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class TransactieController {
    private final TransactieService service;
    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    public TransactieController(TransactieService Service)
    {
        service = Service;
    }

    @PostMapping("/api/maakTransactie")
    public ResponseEntity<Void> slaTransactieOp(
            @RequestBody @Valid TransactieCreateDTO dto)
    {
            Transactie model = dto.naarTransactie();
            service.slaTransactieOp(model);
            return  ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @GetMapping("/api/transacties")
    public List<TransactieDTO> leesTransacties() {
        List<TransactieDTO> responses = new ArrayList<>();
        List<Transactie> transacties = service.LeesTransacties();
        for (Transactie t : transacties) {
            responses.add(new TransactieDTO(t));
        }
        return responses;
    }

}
