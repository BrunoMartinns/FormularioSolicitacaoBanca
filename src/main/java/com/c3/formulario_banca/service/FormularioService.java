package com.c3.formulario_banca.service;

import com.c3.formulario_banca.entidades.FormularioDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class FormularioService {

    public byte[] gerarPdf(FormularioDTO formulario) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
        // Criação do documento PDF e do escritor
        Document document = new Document();
        PdfWriter.getInstance(document, baos);

        // FONTES ::

        BaseFont baseFont = BaseFont.createFont("C:\\Windows\\Fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font10 = new Font(baseFont, 10);  // Definindo a fonte Arial, tamanho 10
        Font fontBold = new Font(baseFont, 10, Font.BOLD);
        Font fontBold12 = new Font(baseFont, 12, Font.BOLD);
        Font font8= new Font(baseFont, 8);
        Font font9= new Font(baseFont, 9);

        // Abrir o documento para adicionar conteúdo
        document.open();

        //TABELA INICIAL:

        PdfPTable table1 = new PdfPTable(3);
        float[] columnWidths1 = {1f, 1f, 1f};
        table1.setWidths(columnWidths1);
        table1.setWidthPercentage(113);

        // primeira coluna:

        Paragraph titulo = new Paragraph("SOLICITAÇÃO DE REVISÃO DE \n PROVA \n", fontBold12);
        titulo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

        PdfPCell cell1 = new PdfPCell();
        cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
        cell1.addElement(titulo);

        Paragraph additionalTextEspacoColuna1 = new Paragraph(" ", font9);
        cell1.addElement(additionalTextEspacoColuna1);

        Paragraph additionalText = new Paragraph("Regulamentada pela Resolução 04/2007 - CONSEPE \n Instrução de Serviço 05/2007 - DAR", font8);
        additionalText.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        cell1.addElement(additionalText);

        Paragraph additionalText2EspacoColuna1 = new Paragraph(" ", font9);
        cell1.addElement(additionalText2EspacoColuna1);

        Paragraph additionalText2 = new Paragraph("Nº     /2020 (digital)      Período: 2020.1", fontBold);
        additionalText2.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        cell1.addElement(additionalText2);

        cell1.setColspan(1);

        table1.addCell(cell1);
        // ------------------------------------------------------------------------------------------------------//
        
        // segunda coluna:

        Paragraph titulo2 = new Paragraph("UNIVERSIDADE CATÓLICA DE \n PERNAMBUCO", fontBold12);
        titulo2.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

        PdfPCell cell2 = new PdfPCell();
        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
        cell2.addElement(titulo2);

        Paragraph additionalTextEspaco = new Paragraph(" ", font9);
        cell2.addElement(additionalTextEspaco);

        Paragraph additionalTextColuna2 = new Paragraph("Diretoria de Gestão Escolar - SEAD", font9);
        additionalTextColuna2.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        cell2.addElement(additionalTextColuna2);
        
        Paragraph additionalTextEspaco2 = new Paragraph(" ", font9);
        cell2.addElement(additionalTextEspaco2);

        Paragraph additionalText2Coluna2 = new Paragraph("Setor responsável pelo encaminhamento: Secretaria do CCT", font9);
        additionalText2Coluna2.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        cell2.addElement(additionalText2Coluna2);

        cell2.setColspan(1);

        table1.addCell(cell2);
        table1.addCell(titulo2);
        document.add(table1);

        // ------------------------------------------------------------------------------------------------------//
        
        // terceira coluna:

            // ******************* foto aqui ***************************

        // ------------------------------------------------------------------------------------------------------//
       
        document.add(new Paragraph("   "));

        // INICIO DA SEGUNDA TABELA:

        PdfPTable table2 = new PdfPTable(2);
        float[] columnWidths2 = {1f, 2f};
        table2.setWidths(columnWidths2);
        table2.setWidthPercentage(113);

        PdfPCell cell1table2 = new PdfPCell(new Paragraph("Numero de Matricula:", fontBold));
        cell1table2.setPadding(5f); // Ajuste na espessura da borda
        cell1table2.setHorizontalAlignment(PdfPCell.ALIGN_LEFT); // Alinhamento do texto

        PdfPCell cell2table2 = new PdfPCell(new Paragraph("Nome do aluno:", fontBold));
        cell2table2.setPadding(5f); // Ajuste na espessura da borda
        cell2table2.setHorizontalAlignment(PdfPCell.ALIGN_LEFT); // Alinhamento do texto

        table2.addCell(cell1table2);
        table2.addCell(cell2table2);

        document.add(table2);
        // ------------------------------------------------------------------------------------------------------//
        document.add(new Paragraph("   "));

        // INICIO DA TERCEIRA TABELA:
        
        PdfPTable table3 = new PdfPTable(3);
        float[] columnWidths3 = {1f, 1f, 1f};
        table3.setWidths(columnWidths3);
        table3.setWidthPercentage(113);

        table3.addCell(new Paragraph("Curso:", fontBold));
        table3.addCell(new Paragraph("Nome da disciplina:", fontBold));
        table3.addCell(new Paragraph("Código / turma:", fontBold));
        document.add(table3);
        // ------------------------------------------------------------------------------------------------------//
       
        document.add(new Paragraph("   "));
        // INICIO DA QUINTA TABELA:

        PdfPTable table5 = new PdfPTable(2);
        float[] columnWidths5 = {2f, 1f};
        table5.setWidths(columnWidths5);
        table5.setWidthPercentage(113);

        table5.addCell(new Paragraph("Ao sr. professor:", fontBold));
        table5.addCell(new Paragraph("Funcionário responsável:", fontBold));
        document.add(table5);

        // ------------------------------------------------------------------------------------------------------//

        // INICIO DA SEXTA TABELA:

        PdfPTable table6 = new PdfPTable(3);
        float[] columnWidths6 = {1f, 1f, 1f};
        table6.setWidths(columnWidths6);
        table6.setWidthPercentage(113);

        table6.addCell(new Paragraph("Data:", fontBold));
        table6.addCell(new Paragraph("Data da realização da prova:", fontBold));
        table6.addCell(new Paragraph("Data da divulgação da nota:", fontBold));
        document.add(table6);

        // ------------------------------------------------------------------------------------------------------//

        document.add(new Paragraph("   "));

        // Fechar o documento
        document.close();

        // Retornar o conteúdo PDF gerado em um array de bytes
        return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }
}

