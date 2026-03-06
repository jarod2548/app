package org.transaction.api;

import org.transaction.domain.Transactie;
import org.transaction.service.TransactieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
@RestController
public class TransactieController {
    private final TransactieService service;

    public TransactieController(TransactieService Service)
    {
        service = Service;
    }

    @PostMapping("/api/maakTransactie")
    public void slaTransactieOp()
    {

    }
    @GetMapping("/api/transacties")
    public List<Transactie> leesTransacties()
    {
        return service.LeesTransacties();
    }

}
