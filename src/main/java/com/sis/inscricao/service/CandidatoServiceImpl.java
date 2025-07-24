package com.sis.inscricao.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sis.inscricao.model.Candidato;
import com.sis.inscricao.model.CandidatoSala;
import com.sis.inscricao.model.Curso;
import com.sis.inscricao.model.Sala;
import com.sis.inscricao.repository.CandidatoRepository;
import com.sis.inscricao.repository.CandidatoSalaRepository;
import com.sis.inscricao.repository.SalaRepository;

@Service
public class CandidatoServiceImpl implements CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private CandidatoSalaRepository candidatoSalaRepository;
    @Autowired
    SalaRepository salaRepository;

    @Override
    public Candidato salvarCandidato(Candidato candidato) {
        return candidatoRepository.save(candidato);
    }

    @Override
    public List<Candidato> listarCandidatos() {
        return candidatoRepository.findAll();
    }

    @Override
    public List<Candidato> listaHoje() {
        LocalDate atual = LocalDate.now();
        return candidatoRepository.findByData_Insc(atual);
    }

    //Acrescimo
    @Override
    public String alocarCandidato(Long candidatoId) {
        Candidato candidato = candidatoRepository.findById(candidatoId)
                .orElseThrow(() -> new RuntimeException("Candidato não encontrado"));

        // Verifique se já existem alocações para o candidato
        List<CandidatoSala> alocacoesExistentes = candidatoSalaRepository.findByCandidato(candidato);

        // Verifica se o candidato já está alocado em uma sala para a primeira opção
        if (alocacoesExistentes.stream().anyMatch(alocacao
                -> alocacao.getSala().getCurso().equals(candidato.getCursoPrimeiraOpcao()))) {
            return "Candidato já alocado na sala para a primeira opção.";
        }

        // Verifica se o candidato já está alocado em uma sala para a segunda opção
        if (alocacoesExistentes.stream().anyMatch(alocacao
                -> alocacao.getSala().getCurso().equals(candidato.getCursoSegundaOpcao()))) {
            return "Candidato já alocado na sala para a segunda opção.";
        }

        // Lógica para encontrar salas disponíveis para cada curso
        Sala salaPrimeiraOpcao = encontrarSalaDisponivel(candidato.getCursoPrimeiraOpcao());
        Sala salaSegundaOpcao = encontrarSalaDisponivel(candidato.getCursoSegundaOpcao());

        // Alocar na sala da primeira opção
        if (salaPrimeiraOpcao != null) {
            CandidatoSala candidatoSala1 = new CandidatoSala();
            candidatoSala1.setCandidato(candidato);
            candidatoSala1.setSala(salaPrimeiraOpcao);
            candidatoSala1.setDataAlocacao(LocalDateTime.now());
            candidatoSalaRepository.save(candidatoSala1);
        }

        // Alocar na sala da segunda opção
        if (salaSegundaOpcao != null) {
            CandidatoSala candidatoSala2 = new CandidatoSala();
            candidatoSala2.setCandidato(candidato);
            candidatoSala2.setSala(salaSegundaOpcao);
            candidatoSala2.setDataAlocacao(LocalDateTime.now());
            candidatoSalaRepository.save(candidatoSala2);
        }

        return "Candidato alocado nas salas correspondentes.";
    }

    @Override
    public Sala encontrarSalaDisponivel(Curso curso) {
        List<Sala> salas = salaRepository.findAll();

        for (Sala sala : salas) {
            // Verifica se a sala existe e se a capacidade é maior que 0
            if (isSalaDisponivel(sala, curso)) {
                return sala; // Retorna a primeira sala disponível encontrada
            }
        }
        return null; // Retorna null se não encontrar salas disponíveis
    }

    @Override
    public boolean isSalaDisponivel(Sala sala, Curso curso) {
        // Verifica se a sala existe e se a capacidade é maior que 0
        if (sala != null && sala.getCapacidade() > 0) {
            //  lógica adicional se necessário, como
            // verificar se a sala está alocada em um horário específico para o curso
            return true; // A sala está disponível
        }
        return false; // A sala não está disponível
    }

    @Override
    public void alocarCandidatoNaSala(Candidato candidato, Sala sala) {
        CandidatoSala candidatoSala = new CandidatoSala();
        candidatoSala.setCandidato(candidato);
        candidatoSala.setSala(sala);
        candidatoSala.setDataAlocacao(LocalDateTime.now());
        candidatoSalaRepository.save(candidatoSala);

    }

}
