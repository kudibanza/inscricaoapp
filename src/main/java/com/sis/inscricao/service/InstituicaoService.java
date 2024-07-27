package com.sis.inscricao.service;

import java.util.List;

import com.sis.inscricao.model.Instituicao;

public interface InstituicaoService {
    Instituicao salvarInstituicao(Instituicao instituicao);
    List<Instituicao> listarInstituicoes();
}
