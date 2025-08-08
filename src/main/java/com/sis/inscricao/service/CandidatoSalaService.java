package com.sis.inscricao.service;

import java.util.List;

import com.sis.inscricao.model.CandidatoSala;

public interface CandidatoSalaService {
    CandidatoSala alocar(CandidatoSala candidatosala);
    List<CandidatoSala> listarCandidatoSalas();
}
