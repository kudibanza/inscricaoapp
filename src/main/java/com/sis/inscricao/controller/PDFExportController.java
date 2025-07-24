package com.sis.inscricao.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sis.inscricao.model.Candidato;
import com.sis.inscricao.model.Code;
import com.sis.inscricao.model.Curso;
import com.sis.inscricao.repository.CandidatoRepository;
import com.sis.inscricao.repository.CodeRepository;
import com.sis.inscricao.repository.CursoRepository;
import com.sis.inscricao.service.PDFService;

@RestController
public class PDFExportController {

    @Autowired
    private PDFService pdfService;

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private CandidatoRepository candidatoRepository;
    @Autowired
    private CodeRepository codeRepository;

    @GetMapping("/cursos/pdf")
    public ResponseEntity<byte[]> gerarPdf() {
        List<Curso> cursos = cursoRepository.findAll(); // Busca todos os clientes do banco de dados

        ByteArrayOutputStream pdfOutput = pdfService.gerarCursosPdf(cursos);
        byte[] pdfBytes = pdfOutput.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=cursos.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

     // Novo endpoint para gerar PDF de um Curso específico
    @GetMapping("/cursos/pdf/{id}")
    public ResponseEntity<byte[]> gerarPdfCurso(@PathVariable Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        ByteArrayOutputStream pdfOutput = pdfService.gerarPdfCurso(curso);
        byte[] pdfBytes = pdfOutput.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=curso_" + id + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

       // Novo endpoint para gerar PDF de um Ficha específico
       @GetMapping("/candidato/pdf/{id}")
       public ResponseEntity<byte[]> gerarPdfCandidato(@PathVariable Long id) {
           Candidato candidato = candidatoRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("Candidato não encontrado"));

        
           ByteArrayOutputStream pdfOutput = pdfService.gerarPdfFicha(candidato);
           byte[] pdfBytes = pdfOutput.toByteArray();
   
           HttpHeaders headers = new HttpHeaders();
           headers.add("Content-Disposition", "attachment; filename=ficha_inscricao_" + id + ".pdf");
                candidato.setPrint(true);
                candidatoRepository.save(candidato);//tira a lista de não impresso
           return ResponseEntity.ok()
                   .headers(headers)
                   .contentType(MediaType.APPLICATION_PDF)
                   .body(pdfBytes);
       }

       @GetMapping("/codes/pdf")
       public ResponseEntity<byte[]> gerarCodesPdf() {
           List<Code> codes = codeRepository.findByAtivo(true); // Busca todos os clientes do banco de dados
   
           ByteArrayOutputStream pdfOutput = pdfService.gerarCodesPdf(codes);
           byte[] pdfBytes = pdfOutput.toByteArray();
   
           HttpHeaders headers = new HttpHeaders();
           headers.add("Content-Disposition", "attachment; filename=cursos.pdf");
   
           return ResponseEntity.ok()
                   .headers(headers)
                   .contentType(MediaType.APPLICATION_PDF)
                   .body(pdfBytes);
       }

}//end