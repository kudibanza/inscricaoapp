package com.sis.inscricao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sis.inscricao.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByInstituicaoId(Long instituicaoId);
}
