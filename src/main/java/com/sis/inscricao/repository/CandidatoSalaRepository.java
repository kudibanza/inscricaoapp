package com.sis.inscricao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sis.inscricao.model.Candidato;
import com.sis.inscricao.model.CandidatoSala;

public interface CandidatoSalaRepository extends JpaRepository<CandidatoSala, Long> {
    List<CandidatoSala> findByCandidato(Candidato candidato);
}
