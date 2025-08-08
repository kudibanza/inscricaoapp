package com.sis.inscricao.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sis.inscricao.model.Candidato;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    @Query("SELECT c FROM Candidato c WHERE c.data_insc = :dataAtual AND c.print = false")
    List<Candidato> findByData_Insc(@Param("dataAtual") LocalDate dataAtual);

     @Query("SELECT c FROM Candidato c WHERE c.cursoPrimeiraOpcao = :cursoId")
    List<Candidato> findCandidatoByCursoId(@Param("cursoId")Long cursoId);
}
