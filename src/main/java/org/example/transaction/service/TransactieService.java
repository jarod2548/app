package org.example.transaction.service;

import org.example.dto.UserDto;
import org.example.transaction.domain.Transactie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TransactieService {

    public List<Transactie>LeesTransacties()
    {
        List<Transactie> transacties = new ArrayList<>();
        return transacties;
    }
}
