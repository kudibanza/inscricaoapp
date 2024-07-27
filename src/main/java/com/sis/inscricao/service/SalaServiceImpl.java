package com.sis.inscricao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sis.inscricao.model.Sala;
import com.sis.inscricao.repository.SalaRepository;

@Service
public class SalaServiceImpl implements SalaService {
    @Autowired
    private SalaRepository salaRepository;

    @Override
    public Sala salvarSala(Sala sala) {
        return salaRepository.save(sala);
    }

    @Override
    public List<Sala> listarSalas() {
        return salaRepository.findAll();
    }
}