package com.sis.inscricao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AplicacaoController {
    
    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/form")
    public String getForm() {
        return "form";
    }
    
}
