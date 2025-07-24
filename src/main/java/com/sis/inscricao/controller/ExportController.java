package com.sis.inscricao.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sis.inscricao.service.ExcelExportService;

@RestController
public class ExportController {
     @Autowired
    private ExcelExportService ExportService;

    @GetMapping("/exportcodes")
    public ResponseEntity<byte[]> exportCodes() {
        try {
            byte[] excelData = ExportService.exportCodesToExcel();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=codes.xlsx");
            return new ResponseEntity<>(excelData, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/exportcandidatos")
    public ResponseEntity<byte[]> exportCandidatos() {
        try {
            byte[] excelData = ExportService.exportCandidatosToExcel();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=candidatos.xlsx");
            return new ResponseEntity<>(excelData, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}//ENd
