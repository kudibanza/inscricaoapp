package com.sis.inscricao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sis.inscricao.model.Candidato;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
}
