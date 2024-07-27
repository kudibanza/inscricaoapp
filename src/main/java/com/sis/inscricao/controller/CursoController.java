package com.sis.inscricao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sis.inscricao.model.Curso;
import com.sis.inscricao.service.CursoService;
import com.sis.inscricao.service.InstituicaoService;


@Controller
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @Autowired
    private InstituicaoService instituicaoService;

    @GetMapping("curso/curso")
    public String mostrarFormulario(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("instituicoes", instituicaoService.listarInstituicoes());
        model.addAttribute("cursos", cursoService.listarCursos());
        return "curso/curso";
    }

    @PostMapping("/curso")
    public String processarCurso(Curso curso) {
        cursoService.salvarCurso(curso);
        return "redirect:/curso/curso";
    }
}
