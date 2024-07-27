package com.sis.inscricao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sis.inscricao.model.Instituicao;
import com.sis.inscricao.service.InstituicaoService;

@Controller
public class InstituicaoController {
    @Autowired
    private InstituicaoService instituicaoService;

    @GetMapping("uo/instituicao")
    public String mostrarFormulario(Model model) {
        model.addAttribute("instituicao", new Instituicao());
        model.addAttribute("instituicoes", instituicaoService.listarInstituicoes());
        return "uo/instituicao";
    }

    @PostMapping("/instituicao")
    public String processarInstituicao(Instituicao instituicao) {
        instituicaoService.salvarInstituicao(instituicao);
        return "redirect:uo/instituicao";
    }
}