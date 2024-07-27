package com.sis.inscricao.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sis.inscricao.model.Curso;
import com.sis.inscricao.model.Sala;
import com.sis.inscricao.repository.CursoRepository;
import com.sis.inscricao.service.InstituicaoService;
import com.sis.inscricao.service.SalaService;

@Controller
public class SalaController {
    @Autowired
    private SalaService salaService;

    @Autowired
    private InstituicaoService instituicaoService;
   
    @Autowired
    CursoRepository cursoRepository;

    @GetMapping("sala/sala")
    public String mostrarFormulario(Model model) {
        model.addAttribute("sala", new Sala());
        model.addAttribute("instituicoes", instituicaoService.listarInstituicoes());
       
        model.addAttribute("salas", salaService.listarSalas());
        return "sala/sala";
    }

   
    @PostMapping("/sala")
    public String processarSala(Sala sala) {
        salaService.salvarSala(sala);
        return "redirect:sala/sala";
    }

      @GetMapping("/cursos/{instituicaoId}")
    @ResponseBody
    public List<Curso> getCursosPorInstituicao(@PathVariable Long instituicaoId) {
        return cursoRepository.findByInstituicaoId(instituicaoId);
    }

}