package org.app.transaction.service;

import org.app.Account.infrastructure.UserDBO;
import org.app.Account.infrastructure.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.app.transaction.repository.TransactieDBO;
import org.app.transaction.domain.Transactie;
import org.app.transaction.repository.TransactieRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactieService {

    private  final TransactieRepository transactieRepository;
    private final UserRepository userRepository;

    public TransactieService(TransactieRepository transactieRepository, UserRepository userRepository) {
        this.transactieRepository = transactieRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public Transactie slaTransactieOp(Transactie model, UUID userID)
    {
            UserDBO userDBO = userRepository.getReferenceById(userID);
            TransactieDBO dbo = model.naarDBO(userDBO);
            TransactieDBO saved = transactieRepository.save(dbo);
            return new Transactie(saved);
    }

    public List<Transactie> leesTransacties()
    {
        List<TransactieDBO> dboList = transactieRepository.findAll();
        List<Transactie> transacties = new ArrayList<>();
        for(TransactieDBO dbo : dboList)
        {
            transacties.add(new Transactie(dbo));
        }
        return transacties;
    }


}
