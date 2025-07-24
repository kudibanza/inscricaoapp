package com.sis.inscricao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AplicacaoController {

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/main")
    public String getIndex2() {
        return "main";
    }

    @GetMapping("/form")
    public String getForm() {
        return "resultado";
    }

    @GetMapping("/alocar")
    public String getAlocar() {
        return "alocar";
    }

}
