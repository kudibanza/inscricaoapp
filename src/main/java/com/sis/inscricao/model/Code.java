package com.sis.inscricao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigo;

    private boolean ativo;

    private int numeroDeOpcoes; // Atributo para definir o número de opções

    public Code() {
        this.ativo = true;
    } 

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getNumeroDeOpcoes() {
        return numeroDeOpcoes;
    }

    public void setNumeroDeOpcoes(int numeroDeOpcoes) {
        this.numeroDeOpcoes = numeroDeOpcoes;
    }
    
}
