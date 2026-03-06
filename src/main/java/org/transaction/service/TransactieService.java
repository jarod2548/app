package org.transaction.service;


import org.transaction.repository.TransactieDBO;
import org.transaction.domain.Transactie;
import org.transaction.repository.TransactieRepository;
import org.springframework.stereotype.Service;

import javax.xml.validation.Validator;
import java.util.ArrayList;
import java.util.List;
@Service
public class TransactieService {

    private  final TransactieRepository repository;
    private final Validator validator;

    public TransactieService(TransactieRepository Repository, Validator Validator){
        validator = Validator;
        repository = Repository;
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
