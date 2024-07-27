package com.sis.inscricao.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sis.inscricao.model.Code;
import com.sis.inscricao.repository.CodeRepository;
import com.sis.inscricao.service.CodeService;

@Controller
public class CodeController {

    @Autowired
    private CodeService codeService;

    @Autowired
    private CodeRepository codeRepository;

    @GetMapping("/code")
    public String mostrarFormulario(Model model) {
       Code code=new Code();
       code.setCodigo(UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase());
       code.setAtivo(true);
        model.addAttribute("code", code);
        model.addAttribute("codes", codeService.listarCodes()); // Lista de códigos ativos
        return "code"; // Retorna o nome da view
    }

    //Imprime somente os codes ativos
    @GetMapping("/codein")
    public String codeAtivos(Model model) {
       Code code=new Code();
       code.setCodigo(UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase());
       code.setAtivo(true);
       List<Code> codeAtivos = codeService.findAtiveCodes();
        model.addAttribute("code", code);
        model.addAttribute("codes", codeAtivos); // Lista de códigos ativos
        return "code"; // Retorna o nome da view
    }

    @PostMapping("/code")
    public String cadastrarCodigo(@ModelAttribute Code code) {
        codeService.salvarCode(code); // Salva o código com o número de opções
        return "redirect:/code"; // Redireciona após salvar
    }

 @GetMapping("/code/{codigo}")
    public ResponseEntity<?> getNumOpcoes(@PathVariable String codigo) {
        Code code = codeRepository.findByCodigo(codigo);
        if (code != null) {
            return ResponseEntity.ok(code.getNumeroDeOpcoes());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Código Inválido");
    }


}

