package com.sis.inscricao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sis.inscricao.model.CandidatoSala;
import com.sis.inscricao.repository.CandidatoSalaRepository;

@Service
public class CandidatoSalaServiceImpl implements CandidatoSalaService {
    @Autowired
    private CandidatoSalaRepository candidatosalaRepository;

    @Override
    public CandidatoSala alocar(CandidatoSala candidatosala) {
        return candidatosalaRepository.save(candidatosala);
    }

    @Override
    public List<CandidatoSala> listarCandidatoSalas() {
        return candidatosalaRepository.findAll();
    }
}