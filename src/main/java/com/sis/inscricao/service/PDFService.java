package com.sis.inscricao.service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import com.sis.inscricao.model.Candidato;
import com.sis.inscricao.model.Code;
import com.sis.inscricao.model.Curso;

@Service
public class PDFService {

     public ByteArrayOutputStream gerarCursosPdf(List<Curso> cursos) {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();
            //Adicionando Título
            document.add(new Paragraph("Lista de Cursos"));
            document.add(new Paragraph(" ")); // Espaço em branco

             // Criando a tabela com 3 colunas
             PdfPTable table = new PdfPTable(3);
             table.setWidthPercentage(100); // Define a largura da tabela para 100%
             table.setSpacingBefore(10f); // Espaço antes da tabela
             table.setSpacingAfter(10f); // Espaço depois da tabela

             // Adicionando cabeçalhos
            table.addCell("ID");
            table.addCell("Curso");
            table.addCell("Instituição");

             //Adicionando os dados
            for(Curso curso: cursos){
                table.addCell(String.valueOf(curso.getId()));
                table.addCell(curso.getDescricao());
                table.addCell(curso.getInstituicao().getSigla()); 
            }  
        // Adicionando a tabela ao documento
           document.add(table);
           document.add(new Paragraph("Dados Obtidos na Base de Dados ServiceDB"));
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return baos; // Retorna o fluxo de saída com o PDF gerado
    }

    //Novo método
    public ByteArrayOutputStream gerarPdfCurso(Curso curso) {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

             // Adicionando logotipo
             String logoPath = "src\\main\\resources\\static\\img\\instic-logo.png";
             Image logo = Image.getInstance(logoPath);
            logo.scaleToFit(140, 120); // Ajusta o tamanho do logotipo
            logo.setAlignment(Image.ALIGN_LEFT); // Centraliza a imagem
            document.add(logo); // Adiciona o logotipo ao documento

            // Adicionando título
            document.add(new Paragraph("Detalhes do Curso"));
            document.add(new Paragraph(" ")); // Espaço em branco

            // Criando a tabela com 3 colunas
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100); // Define a largura da tabela para 100%
            table.setSpacingBefore(10f); // Espaço antes da tabela
            table.setSpacingAfter(10f); // Espaço depois da tabela

            // Adicionando cabeçalhos
           table.addCell("ID");
           table.addCell(String.valueOf(curso.getId()));
           table.addCell("Curso");
           table.addCell(curso.getDescricao());
           table.addCell("Instituição");
           table.addCell(curso.getInstituicao().getSigla());

            // Adicionando informações do cliente
            document.add(new Paragraph("ID: " + curso.getId()));
            document.add(new Paragraph("Curso: " + curso.getDescricao()));
            document.add(new Paragraph("Instituição: " + curso.getInstituicao().getSigla()));

         
            //ADICIONAR A TABELA AO DOC
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();          
        }finally {
            try {
                baos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return baos; // Retorna o fluxo de saída com o PDF gerado
    }

     //Gerar Ficha de Inscrição
     public ByteArrayOutputStream gerarPdfFicha(Candidato candidato) {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();
            // Capturando a data de hoje
        LocalDate dataHoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy");
        String dataFormatada = dataHoje.format(formatter);
            int ano=dataHoje.getYear();
            for (int i = 0; i < 2; i++) {
             // Adicionando logotipo
             String logoPath = "src\\main\\resources\\static\\img\\instic-logo.png";
             Image logo = Image.getInstance(logoPath);
            logo.scaleToFit(140, 120); // Ajusta o tamanho do logotipo
            logo.setAlignment(Image.ALIGN_CENTER); // Centraliza a imagem
            document.add(logo); // Adiciona o logotipo ao documento

            // Adicionando título
            Paragraph titulo=new Paragraph(" ");
            Chunk t1=new Chunk("Inscrição para Exame de Acesso "+ano+"/"+(ano+1),FontFactory.getFont(FontFactory.HELVETICA_BOLD));
            titulo.add(t1);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(titulo);
            document.add(new Paragraph(" ")); // Espaço em branco

            // Criando a tabela com 3 colunas
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100); // Define a largura da tabela para 100%
            table.setSpacingBefore(10f); // Espaço antes da tabela
            table.setSpacingAfter(10f); // Espaço depois da tabela
            Font negrito = new Font(Font.BOLD);
            // Adicionando cabeçalhos
           table.addCell(new Paragraph("Nome", negrito));
           table.addCell(candidato.getNome());
           table.addCell(new Paragraph("BI", negrito));
           table.addCell(candidato.getBi());
           table.addCell(new Paragraph("Curso 1ª Opção", negrito));
           table.addCell(candidato.getCursoPrimeiraOpcao().getDescricao());
           table.addCell(new Paragraph("Curso 2ª Opção", negrito));
            if(candidato.getCursoSegundaOpcao()!=null)
                 table.addCell(candidato.getCursoSegundaOpcao().getDescricao());
           else
                table.addCell("N/A");
           table.addCell(new Paragraph("Periodo", negrito));
           table.addCell(candidato.getPeriodo());
           table.addCell(new Paragraph("Contacto (s)", negrito));
           table.addCell(candidato.getTelefone());

            // Adicionando informações do cliente
            Paragraph ficha=new Paragraph("Ficha de Inscrição n.º: " + candidato.getCode().getCodigo());
            ficha.setAlignment(Paragraph.ALIGN_RIGHT);
         document.add(ficha);
         
            //ADICIONAR A TABELA AO DOC
           
                document.add(table);
            //Data    
                Paragraph p1=new Paragraph("Luanda aos, "+dataFormatada);
                p1.setAlignment(Paragraph.ALIGN_CENTER);
             document.add(p1);
             document.add(new Paragraph(" "));
            //Assinatura
            Paragraph p2=new Paragraph("Ass. do(a) Funcionário(a)                                      Ass. do(a) Candidato(a)");
                p2.setAlignment(Paragraph.ALIGN_CENTER);
             document.add(p2);

             Paragraph p3=new Paragraph("_______________________________                             ____________________________");
                p3.setAlignment(Paragraph.ALIGN_CENTER);
             document.add(p3);
             document.add(new Paragraph(" "));
             // Obs
             Paragraph p4=new Paragraph("Obs: Proteger este recibo, será exigido no dia do exame.");
                p4.setAlignment(Paragraph.ALIGN_RIGHT);
             document.add(p4);
                //site
             Paragraph p5=new Paragraph("Informações: https://instic.uniluanda.ao    ou   https://www.facebook.com/ISUTIC2020/");
             p5.setAlignment(Paragraph.ALIGN_CENTER);
          document.add(p5);
          document.add(new Paragraph("____________________________________________________________________________"));
            }
            
            
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();          
        }finally {
            try {
                baos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return baos; // Retorna o fluxo de saída com o PDF gerado
    }

    public ByteArrayOutputStream gerarCodesPdf(List<Code> codes) {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = null;
        try {
            writer=PdfWriter.getInstance(document, baos);
            document.open();

             // Adicionando logotipo
             String logoPath = "src\\main\\resources\\static\\img\\instic-logo.png";
             Image logo = Image.getInstance(logoPath);
            logo.scaleToFit(140, 120); // Ajusta o tamanho do logotipo
            logo.setAlignment(Image.ALIGN_LEFT); // Centraliza a imagem
            document.add(logo); // Adiciona o logotipo ao documento
            //Adicionando Título
            document.add(new Paragraph("Codes Válidos"));
            document.add(new Paragraph(" ")); // Espaço em branco

             // Criando a tabela com 3 colunas
             PdfPTable table = new PdfPTable(3);
             table.setWidthPercentage(100); // Define a largura da tabela para 100%
             table.setSpacingBefore(10f); // Espaço antes da tabela
             table.setSpacingAfter(10f); // Espaço depois da tabela

             // Adicionando cabeçalhos
            table.addCell("ID");
            table.addCell("Code");
            table.addCell("N.º de Opções");

             //Adicionando os dados
             int count=0;
            for(Code code: codes){
                if (count > 0 && count % 40 == 0) { // Muda de página a cada 20 cursos
                
                document.add(table);
                document.newPage();
                table = new PdfPTable(3); // Reinicia a tabela
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);
                
                // Adicionando cabeçalhos novamente
                table.addCell("ID");
                table.addCell("Code");
                table.addCell("N.º de Opções");
                }
                //
                table.addCell(String.valueOf(code.getId()));
                table.addCell(code.getCodigo());
                table.addCell(String.valueOf(code.getNumeroDeOpcoes())); 
                count++;

                        // Adiciona numeração de páginas
        writer.setPageEvent(new PdfPageEventHelper() {
            public void onEndPage(PdfWriter writer, Document document) {
                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
                        new Phrase(String.format("Página %d", writer.getPageNumber())), 300, 30, 0);
            }
        });
                }

        // Adicionando a tabela ao documento
           document.add(table);
           document.add(new Paragraph("Dados Obtidos na Base de Dados ServiceDB"));
          
     
       
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }finally {
            if (document.isOpen()) {
                document.close();
            }
            try {
                baos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return baos; // Retorna o fluxo de saída com o PDF gerado
    }

}//end