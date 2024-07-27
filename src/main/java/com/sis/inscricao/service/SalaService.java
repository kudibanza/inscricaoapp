package com.sis.inscricao.service;

import java.util.List;

import com.sis.inscricao.model.Sala;

public interface SalaService {
    Sala salvarSala(Sala sala);
    List<Sala> listarSalas();
}
