package com.sis.inscricao.controller;



import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sis.inscricao.model.Candidato;
import com.sis.inscricao.model.Curso;
import com.sis.inscricao.model.CandidatoSala;
import com.sis.inscricao.model.Sala;
import com.sis.inscricao.repository.SalaRepository;
import com.sis.inscricao.repository.CandidatoRepository;
import com.sis.inscricao.service.CandidatoSalaService;
import com.sis.inscricao.service.CandidatoService;
import com.sis.inscricao.service.CursoService;
import com.sis.inscricao.service.SalaService;

@Controller
public class CandidatoSalaController {
    @Autowired
    private CandidatoSalaService candidatosalaService;

    @Autowired
    private SalaService salaService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CandidatoService candidatoService;
   
     @Autowired
    private SalaRepository salaRepository;

     @Autowired
    private CandidatoRepository candidatoRepository;

    @GetMapping("candidatosala/candidatosala")
    public String mostrarFormulario(Model model) {
        model.addAttribute("candidatosala", new CandidatoSala());
        //model.addAttribute("candidatos", candidatoService.listarCandidatos());
        long cursoId=2;
           Curso curso=new Curso();
        curso.setId(cursoId); 
        model.addAttribute("candidatos", candidatoService.findCandidatoByCursoId(curso));
        model.addAttribute("salas", salaService.listarSalas());
        model.addAttribute("cursos", cursoService.listarCursos());
        model.addAttribute("candidatosalas", candidatosalaService.listarCandidatoSalas());
        return "candidatosala/candidatosala";
    }

   
    @PostMapping("/candidatosala")
    public String alocar(CandidatoSala candidatosala) {
       candidatosala.setDataAlocacao(LocalDate.now());
        candidatosalaService.alocar(candidatosala);
        
        return "redirect:candidatosala/candidatosala";
    }

      @GetMapping("/salas/{cursoId}")
    @ResponseBody
    public List<Sala> getSalaPorCurso(@PathVariable Long cursoId) {
        return salaRepository.findSalaByCursoId(cursoId); 
    } 

    
    @GetMapping("/candidatos/{cursoId}")
    @ResponseBody
    public List<Candidato> getPorCandidatoPorCurso(@PathVariable Long cursoId) {
        Curso curso=new Curso();
        curso.setId(cursoId); 
       
        return candidatoRepository.findCandidatoByCursoId(curso);
    } 

}//end