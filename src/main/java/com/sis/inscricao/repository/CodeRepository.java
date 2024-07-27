package com.sis.inscricao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sis.inscricao.model.Code;

public interface CodeRepository extends JpaRepository<Code, Long> {
    Code findByCodigoAndAtivo(String codigo, boolean ativo); // Método para buscar código ativo
    List<Code> findByAtivo(boolean ativo);
    Code findByCodigo(String codigo);
}
