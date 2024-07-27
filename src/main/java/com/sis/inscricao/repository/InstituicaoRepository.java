package com.sis.inscricao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sis.inscricao.model.Instituicao;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
}
