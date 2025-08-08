package com.sis.inscricao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.sis.inscricao.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {

     List<Sala> findSalaByCursoId(Long cursoId);
   
}
