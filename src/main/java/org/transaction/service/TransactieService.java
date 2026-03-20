package org.transaction.service;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.Transactional;
import org.transaction.repository.TransactieDBO;
import org.transaction.domain.Transactie;
import org.transaction.repository.TransactieRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class TransactieService {

    private  final TransactieRepository repository;

    public TransactieService(TransactieRepository Repository){
        repository = Repository;
    }

    @Transactional
    public Transactie slaTransactieOp(Transactie model)
    {
            TransactieDBO dbo = model.naarDBO();
            TransactieDBO saved = repository.save(dbo);
            return new Transactie(saved);
    }

    public List<Transactie> LeesTransacties()
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
