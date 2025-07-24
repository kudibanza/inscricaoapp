package com.sis.inscricao.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String bi;
    private LocalDate data_nasc;
    private String genero;
    private String necessidade;
    private String email;
    private String telefone;
    private String provincia;
    private String municipio;
    private String trabalha;
    private String curso_medio;
    private long ano_conclusao;
    private double nota_medio;
    private String procedencia;
    private String natureza;
    private String financiamento;
    private String periodo;
    private LocalDate data_insc;
    private boolean print;
    private String parceiro;
    private String nome_parceiro;

    @ManyToOne
    @JoinColumn(name = "curso_primeira_opcao_id")
    private Curso cursoPrimeiraOpcao;

    @ManyToOne
    @JoinColumn(name = "curso_segunda_opcao_id")
    private Curso cursoSegundaOpcao;

    @ManyToOne
    @JoinColumn(name = "code_id")
    private Code code; // Novo atributo para armazenar o código

    //Constuctor

    public Candidato() {
        this.print = false;
    }


    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBi() {
        return bi;
    }

    public void setBi(String bi) {
        this.bi = bi;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNecessidade() {
        return necessidade;
    }

    public void setNecessidade(String necessidade) {
        this.necessidade = necessidade;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }

    public String getCurso_medio() {
        return curso_medio;
    }

    public void setCurso_medio(String curso_medio) {
        this.curso_medio = curso_medio;
    }

    public String getFinanciamento() {
        return financiamento;
    }

    public void setFinanciamento(String financiamento) {
        this.financiamento = financiamento;
    }

    public long getAno_conclusao() {
        return ano_conclusao;
    }

    public void setAno_conclusao(long ano_conclusao) {
        this.ano_conclusao = ano_conclusao;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getTrabalha() {
        return trabalha;
    }

    public void setTrabalha(String trabalha) {
        this.trabalha = trabalha;
    }

    public double getNota_medio() {
        return nota_medio;
    }

    public void setNota_medio(double nota_media) {
        this.nota_medio = nota_media;
    }

    public LocalDate getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(LocalDate data_nasc) {
        this.data_nasc = data_nasc;
    }

    public LocalDate getData_insc() {
        return data_insc;
    }

    public void setData_insc(LocalDate data_insc) {
        this.data_insc = data_insc;
    }

    public Curso getCursoPrimeiraOpcao() {
        return cursoPrimeiraOpcao;
    }

    public void setCursoPrimeiraOpcao(Curso cursoPrimeiraOpcao) {
        this.cursoPrimeiraOpcao = cursoPrimeiraOpcao;
    }

    public Curso getCursoSegundaOpcao() {
        return cursoSegundaOpcao;
    }

    public void setCursoSegundaOpcao(Curso cursoSegundaOpcao) {
        this.cursoSegundaOpcao = cursoSegundaOpcao;
    }

    public Boolean getPrint() {
        return print;
    }

    public void setPrint(Boolean print) {
        this.print = print;
    }

    public String getParceiro() {
        return parceiro;
    }

    public void setParceiro(String parceiro) {
        this.parceiro = parceiro;
    }

    public String getNome_parceiro() {
        return nome_parceiro;
    }

    public void setNome_parceiro(String nome_parceiro) {
        this.nome_parceiro = nome_parceiro;
    }


}// End
