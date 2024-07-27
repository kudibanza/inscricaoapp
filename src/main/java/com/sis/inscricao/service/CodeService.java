package com.sis.inscricao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sis.inscricao.model.Code;
import com.sis.inscricao.repository.CodeRepository;

@Service
public class CodeService {
    
    @Autowired
    private CodeRepository codeRepository;

      public Code encontrarPorCodigo(String codigo) {
        return codeRepository.findByCodigoAndAtivo(codigo, true); // Verifica se o código está ativo
    }

    public void desativar(Code code) {
        code.setAtivo(false); // Define o código como inativo
        codeRepository.save(code);
    }

    public void salvarCode(Code code) {
        codeRepository.save(code); // Salva o código no banco de dados
    }

    public List<Code> listarCodes() {
        return codeRepository.findAll(); // Retorna todos os códigos
    }

    public List<Code> findAtiveCodes() {
        return codeRepository.findByAtivo(true); // Retorna todos os códigos
    }
    
    
/* 
    public void remover(Code code) {
        codeRepository.delete(code);
    } 
        */
        
}
