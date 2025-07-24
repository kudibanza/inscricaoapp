package com.sis.inscricao.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {


   @RequestMapping("/error")
   public String handleError() {
       // Retorna a página de erro personalizada
       return "errorPage"; // Nome do arquivo error.html
   }

}

