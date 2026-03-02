package org.example.transaction.service;


import org.example.transaction.DBO.TransactieDBO;
import org.example.transaction.domain.Transactie;
import org.example.transaction.repository.TransactieRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class TransactieService {

    private  final TransactieRepository repository;

    public TransactieService(TransactieRepository Repository){
        repository = Repository;
    }

    public List<Transactie>LeesTransacties2()
    {
        LocalDateTime dateTime = LocalDateTime.of(2023, 10, 1, 10, 0);
        List<Transactie> transacties = new ArrayList<>();
        transacties.add(new Transactie(new BigDecimal(21), "",dateTime ));
        return transacties;
    }
    public boolean slaTransactieOp()
    {
        boolean succes = false;

        return succes;

    }

    public List<Transactie>LeesTransacties()
    {
        List<TransactieDBO> dboList = repository.findAll();
        List<Transactie> transacties = new ArrayList<>();
        for(TransactieDBO dbo : dboList)
        {
            transacties.add(new Transactie(dbo));
        }
        return transacties;
    }
}
