package com.sis.inscricao.service;

import java.util.List;

import com.sis.inscricao.model.Curso;

public interface CursoService {
    Curso salvarCurso(Curso curso);
    List<Curso> listarCursos();
}
