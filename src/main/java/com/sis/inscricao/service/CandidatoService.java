package com.sis.inscricao.service;

import java.util.List;

import com.sis.inscricao.model.Candidato;
import com.sis.inscricao.model.Curso;
import com.sis.inscricao.model.Sala;

public interface CandidatoService {
    Candidato salvarCandidato(Candidato candidato);
    List<Candidato> listarCandidatos();
    //Acrescimo
    String alocarCandidato(Long candidatoId);
    Sala encontrarSalaDisponivel(Curso curso);
    boolean isSalaDisponivel(Sala sala, Curso curso);
    void alocarCandidatoNaSala(Candidato candidato, Sala sala);

}
