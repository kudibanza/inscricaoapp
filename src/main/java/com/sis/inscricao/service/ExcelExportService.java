package com.sis.inscricao.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sis.inscricao.model.Candidato;
import com.sis.inscricao.model.Code;
import com.sis.inscricao.repository.CandidatoRepository;
import com.sis.inscricao.repository.CodeRepository;

@Service
public class ExcelExportService {

    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private CandidatoRepository candidatoRepository;

    public byte[] exportCodesToExcel() throws IOException {
        List<Code> codes = codeRepository.findByAtivo(true);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Codes");

        // Criar cabeçalhos
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Código");
        headerRow.createCell(2).setCellValue("Número");

        // Preencher os dados
        int rowNum = 1;
        for (Code code : codes) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(code.getId());
            row.createCell(1).setCellValue(code.getCodigo());
            row.createCell(2).setCellValue(code.getNumeroDeOpcoes());
        }

        // Escrever para um ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
    
    public byte[] exportCandidatosToExcel() throws IOException {
        List<Candidato> inscritos = candidatoRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Candidato");

        // Criar cabeçalhos
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("N.º Ficha");
        headerRow.createCell(2).setCellValue("Nome Completo");
        headerRow.createCell(3).setCellValue("Bilhete de Identidade");
        headerRow.createCell(4).setCellValue("Sexo");
        headerRow.createCell(5).setCellValue("Idade");
        headerRow.createCell(6).setCellValue("Data de Nascimento");
        headerRow.createCell(7).setCellValue("Província Residência");
        headerRow.createCell(8).setCellValue("Município Residência");
        headerRow.createCell(9).setCellValue("País");
        headerRow.createCell(10).setCellValue("Período de Estudo");
        headerRow.createCell(11).setCellValue("Unidade Orgânica");
        headerRow.createCell(12).setCellValue("Nome do Curso Inscrito no Ensino Superior ");
        headerRow.createCell(13).setCellValue("Curso de 2ª Opção");
        headerRow.createCell(14).setCellValue("Nota de Exame de Acesso");
        headerRow.createCell(15).setCellValue("Admissão");
        headerRow.createCell(16).setCellValue("Matícula pela 1ª vez");
        headerRow.createCell(17).setCellValue("Necessidade de Educação Especial");
        headerRow.createCell(18).setCellValue("Procedência Escolar do Ensino Médio");
        headerRow.createCell(19).setCellValue("Natureza da Escola de Proveniência");
        headerRow.createCell(20).setCellValue("Nome do Curso do Ensino");
        headerRow.createCell(21).setCellValue("Média Final no Ensino Médio");
        headerRow.createCell(22).setCellValue("Financiamento dos Estudos no Ensino Médio");
        headerRow.createCell(23).setCellValue("Trabalhador");
        headerRow.createCell(24).setCellValue("Data Inscrição");
        
        //Cuidando da data
        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-mm-dd"));
        // Preencher os dados
        int rowNum = 1;
        for (Candidato candidato : inscritos) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(candidato.getId());
            row.createCell(1).setCellValue(candidato.getCode().getCodigo());
            row.createCell(2).setCellValue(candidato.getNome());
            row.createCell(3).setCellValue(candidato.getBi());
            row.createCell(4).setCellValue(candidato.getGenero());
            row.createCell(5).setCellValue(calcularIdade(candidato.getData_nasc()));

             // Escrever a data de nascimento com o estilo de data
            Cell dateCell = row.createCell(6);
            dateCell.setCellValue(candidato.getData_nasc());
            dateCell.setCellStyle(dateCellStyle);
  
            row.createCell(7).setCellValue(candidato.getProvincia());
            row.createCell(8).setCellValue(candidato.getMunicipio());
            row.createCell(9).setCellValue("Angola");
            row.createCell(10).setCellValue(candidato.getPeriodo());
            row.createCell(11).setCellValue(candidato.getCursoPrimeiraOpcao().getInstituicao().getNome());
            row.createCell(12).setCellValue(candidato.getCursoPrimeiraOpcao().getDescricao());
            if(candidato.getCursoSegundaOpcao()==null)
                row.createCell(13).setCellValue("N/A");
            else 
                row.createCell(13).setCellValue(candidato.getCursoSegundaOpcao().getDescricao());

            row.createCell(14).setCellValue("");
            row.createCell(15).setCellValue("");
            row.createCell(16).setCellValue("Sim");
            row.createCell(17).setCellValue(candidato.getNecessidade());
            row.createCell(18).setCellValue(candidato.getProcedencia());
            row.createCell(19).setCellValue(candidato.getNatureza());
            row.createCell(20).setCellValue(candidato.getCurso_medio());
            row.createCell(21).setCellValue(candidato.getNota_medio());
            row.createCell(22).setCellValue(candidato.getFinanciamento());
            row.createCell(23).setCellValue(candidato.getTrabalha());
            //data inscricao
            Cell dateCell2 = row.createCell(24);
            dateCell2.setCellValue(candidato.getData_insc());
            dateCell2.setCellStyle(dateCellStyle);
           
        }

        // Escrever para um ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }

    //Para as Listagens
    public byte[] exportListaToExcel() throws IOException {
        List<Candidato> inscritos = candidatoRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Candidato");

        // Criar cabeçalhos
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("N.º Ficha");
        headerRow.createCell(2).setCellValue("Nome Completo");
        headerRow.createCell(3).setCellValue("Bilhete de Identidade");
        headerRow.createCell(4).setCellValue("Sexo");
        headerRow.createCell(5).setCellValue("Período de Estudo");
        headerRow.createCell(6).setCellValue("Nome do Curso Inscrito no Ensino Superior ");
        headerRow.createCell(7).setCellValue("Curso de 2ª Opção");
        
        //Cuidando da data
        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-mm-dd"));
        // Preencher os dados
        int rowNum = 1;
        for (Candidato candidato : inscritos) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(candidato.getId());
            row.createCell(1).setCellValue(candidato.getCode().getCodigo());
            row.createCell(2).setCellValue(candidato.getNome());
            row.createCell(3).setCellValue(candidato.getBi());
            row.createCell(4).setCellValue(candidato.getGenero());

            row.createCell(5).setCellValue(candidato.getPeriodo());
            row.createCell(6).setCellValue(candidato.getCursoPrimeiraOpcao().getDescricao());
            if(candidato.getCursoSegundaOpcao()==null)
                row.createCell(7).setCellValue("N/A");
            else 
                row.createCell(7).setCellValue(candidato.getCursoSegundaOpcao().getDescricao());

        }

        // Escrever para um ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }

     private int calcularIdade(LocalDate datanasc) {
        if (datanasc == null) {
            return 0; // Ou lançar uma exceção, dependendo da sua lógica
        }
        return Period.between(datanasc, LocalDate.now()).getYears();
    }

    //Mandar em PDF
   
}//END

