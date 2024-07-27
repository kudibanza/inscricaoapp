package com.sis.inscricao.controller;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sis.inscricao.model.Candidato;
import com.sis.inscricao.model.Code;
import com.sis.inscricao.service.CandidatoService;
import com.sis.inscricao.service.CodeService;
import com.sis.inscricao.service.CursoService;

@Controller
public class CandidatoController {
    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CodeService codeService;

    @GetMapping("/candidato")
    public String mostrarFormulario(Model model) {
        model.addAttribute("candidato", new Candidato());
        model.addAttribute("cursos", cursoService.listarCursos());
        model.addAttribute("candidatos", candidatoService.listarCandidatos());
        model.addAttribute("codigoInvalido", false); // Adiciona atributo para controle de erro
        return "candidato";
    }

    @GetMapping("/listcandidato")
    public String listaCandidatos(Model model) {
        model.addAttribute("candidatos", candidatoService.listarCandidatos());
        model.addAttribute("codigoInvalido", false); // Adiciona atributo para controle de erro
        return "listcandidato";
    }

    @PostMapping("/saveCandidato")
    public String processarCandidato(Candidato candidato, Model model) {
        candidato.setData_insc(LocalDate.now());
        if (candidato.getCursoPrimeiraOpcao() == null) {
            return "redirect:/candidato"; // Redireciona se a primeira opção não for selecionada
        }

        // Verifica se o código foi fornecido e se ele é válido
        if (candidato.getCode() != null && candidato.getCode().getCodigo() != null) {
         
            Code code = codeService.encontrarPorCodigo(candidato.getCode().getCodigo());
     
            if (code != null) {
                candidato.setCode(code);
                // Desativa o código após o uso
                codeService.desativar(code);
            } else {
                // Código inválido, retorna ao formulário com mensagem de erro
                model.addAttribute("codigoInvalido", true);
                model.addAttribute("cursos", cursoService.listarCursos());
                model.addAttribute("candidatos", candidatoService.listarCandidatos());
                return "candidato"; // Retorna ao formulário
            }
        }

        candidatoService.salvarCandidato(candidato);
        return "redirect:/candidato"; // Redireciona após salvar
    }

    //Acrescimo
     @GetMapping("/alocar")
    public String mostrarForm(Model model) {
        // Adicione atributos ao modelo, se necessário
        return "alocar"; // Retornar o template HTML
    }

    @PostMapping("/alocar")
    public String alocarCandidato(@RequestParam Long candidatoId, Model model) {
        String resultado = candidatoService.alocarCandidato(candidatoId);
        model.addAttribute("mensagem", resultado);
        return "resultado"; // Retornar o template de resultado
    }


}