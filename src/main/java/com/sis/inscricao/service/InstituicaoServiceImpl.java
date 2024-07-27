package com.sis.inscricao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sis.inscricao.model.Instituicao;
import com.sis.inscricao.repository.InstituicaoRepository;

@Service
public class InstituicaoServiceImpl implements InstituicaoService {
    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @Override
    public Instituicao salvarInstituicao(Instituicao instituicao) {
        return instituicaoRepository.save(instituicao);
    }

    @Override
    public List<Instituicao> listarInstituicoes() {
        return instituicaoRepository.findAll();
    }
}
