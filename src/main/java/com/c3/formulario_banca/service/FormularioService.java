package com.c3.formulario_banca.service;

import com.c3.formulario_banca.entidades.FormularioDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;

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

        // ------------------------------------------------------------------------------------------------------//
        
        // terceira coluna:

        InputStream imagemStream = getClass().getClassLoader().getResourceAsStream("static/imagens/Imagem2.jpg");

        byte[] imagemBytes = imagemStream.readAllBytes();
        Image imagem = Image.getInstance(imagemBytes);
        imagem.scaleToFit(190, 190);

        PdfPCell celulaComImagem = new PdfPCell(imagem);
        celulaComImagem.setHorizontalAlignment(Element.ALIGN_CENTER); // Centraliza horizontalmente
        celulaComImagem.setVerticalAlignment(Element.ALIGN_MIDDLE);   // Centraliza verticalmente
        table1.addCell(celulaComImagem);

        document.add(table1);

        // ------------------------------------------------------------------------------------------------------//
       
        document.add(new Paragraph("   "));

        // INICIO DA SEGUNDA TABELA:

        PdfPTable table2 = new PdfPTable(2);
        float[] columnWidths2 = {1f, 2f};
        table2.setWidths(columnWidths2);
        table2.setWidthPercentage(113);

        Paragraph paragraphMatricula = new Paragraph();
        paragraphMatricula.add(new Chunk("Numero de Matricula: ", fontBold));
        paragraphMatricula.add(new Chunk(formulario.getMatricula(), font10));

        PdfPCell cell1table2 = new PdfPCell(paragraphMatricula);
        cell1table2.setPadding(5f); // Ajuste na espessura da borda
        cell1table2.setHorizontalAlignment(PdfPCell.ALIGN_LEFT); // Alinhamento do texto

        Paragraph paragraphNomeAluno = new Paragraph();
        paragraphNomeAluno.add(new Chunk("Nome do aluno: ", fontBold));
        paragraphNomeAluno.add(new Chunk(formulario.getNome(), font10));

        PdfPCell cell2table2 = new PdfPCell(paragraphNomeAluno);
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

        Paragraph paragraphCurso = new Paragraph();
        paragraphCurso.add(new Chunk("Curso: ", fontBold));
        paragraphCurso.add(new Chunk(formulario.getCurso(), font10));

        Paragraph paragraphNomeDisciplina = new Paragraph();
        paragraphNomeDisciplina.add(new Chunk("Nome da disciplina: ", fontBold));
        paragraphNomeDisciplina.add(new Chunk(formulario.getDisciplina(), font10));

        Paragraph paragraphTurma = new Paragraph();
        paragraphTurma.add(new Chunk("Código / turma: ", fontBold));
        paragraphTurma.add(new Chunk(formulario.getCodigoTurma(), font10));

        table3.addCell(new Paragraph(paragraphCurso));
        table3.addCell(new Paragraph(paragraphNomeDisciplina));
        table3.addCell(new Paragraph(paragraphTurma));
        document.add(table3);
        // ------------------------------------------------------------------------------------------------------//
        document.add(new Paragraph("   "));

        // INICIO DA QUARTA TABELA 

        PdfPTable table4 = new PdfPTable(3);
        float[] columnWidths4 = {2f, 3f, 2f};
        table4.setWidths(columnWidths4);
        table4.setWidthPercentage(113);

        String campoProva = formulario.getProva();

        PdfPCell cabecalho = new PdfPCell(new Paragraph("Prova de:", fontBold));
        cabecalho.addElement(new Paragraph("Prova de:", fontBold));

        if(campoProva.equals("1 chamada de 1GQ")){
            cabecalho.addElement(new Paragraph("   x       1ª Chamada de 1GQ", font8));
        } else {
            cabecalho.addElement(new Paragraph(" ____      1ª Chamada de 1GQ", font8));
        }

        if(campoProva.equals("2 chamada de 1GQ")){
            cabecalho.addElement(new Paragraph("   x       2ª Chamada de 1GQ", font8));
        } else {
            cabecalho.addElement(new Paragraph(" ____      2ª Chamada de 1GQ", font8));
        }
        
        if(campoProva.equals("1 chamada de 2GQ")){
            cabecalho.addElement(new Paragraph("   x       1ª Chamada de 2GQ", font8));
        } else {
            cabecalho.addElement(new Paragraph(" ____      1ª Chamada de 2GQ", font8));
        }

        if(campoProva.equals("2 chamada de 2GQ")){
            cabecalho.addElement(new Paragraph("   x       2ª Chamada de 2GQ", font8));
        } else {
            cabecalho.addElement(new Paragraph(" ____      2ª Chamada de 2GQ", font8));
        }

        if(campoProva.equals("Exame Final")){
            cabecalho.addElement(new Paragraph("   x       Exame Final", font8));
        } else {
            cabecalho.addElement(new Paragraph(" ____      Exame Final", font8));
        }

        if(campoProva.equals("Única Avaliação")){
            cabecalho.addElement(new Paragraph("   x       Única Avaliação", font8));
        } else {
            cabecalho.addElement(new Paragraph(" ____      Única Avaliação", font8));
        }
        
        cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
        cabecalho.setRowspan(3);

        table4.addCell(cabecalho);


        Paragraph paragraphHorarioDisciplina = new Paragraph();
        paragraphHorarioDisciplina.add(new Chunk("Horário da disciplina: ", fontBold));
        paragraphHorarioDisciplina.add(new Chunk(formulario.getHorario(), font10));
        

        PdfPCell horarioDisciplina = new PdfPCell(paragraphHorarioDisciplina);
        horarioDisciplina.addElement(new Paragraph(paragraphHorarioDisciplina));
        horarioDisciplina.addElement(new Paragraph("  "));
        horarioDisciplina.setRowspan(1);

        Paragraph paragraphObservacaoAluno = new Paragraph();
        paragraphObservacaoAluno.add(new Chunk("Observação do aluno: ", fontBold));
        paragraphObservacaoAluno.add(new Chunk(formulario.getObservacao(), font10));

        PdfPCell obs = new PdfPCell(new Paragraph(paragraphObservacaoAluno));
        obs.setRowspan(3);

        Paragraph paragraphProfessor = new Paragraph();
        paragraphProfessor.add(new Chunk("Professor: ", fontBold));
        paragraphProfessor.add(new Chunk(formulario.getProfessor(), font10));

        PdfPCell professor = new PdfPCell(paragraphProfessor);
        professor.addElement(new Paragraph(paragraphProfessor));
        professor.addElement(new Paragraph("  "));
        professor.setRowspan(1);

        Paragraph paragraphMotivo= new Paragraph();
        paragraphMotivo.add(new Chunk("Motivo: ", fontBold));
        paragraphMotivo.add(new Chunk(formulario.getMotivo(), font10));

        PdfPCell motivo = new PdfPCell(paragraphMotivo);
        motivo.addElement(new Paragraph(paragraphMotivo));
        motivo.addElement(new Paragraph("  "));
        motivo.setRowspan(1);

        table4.addCell(horarioDisciplina);
        table4.addCell(obs);
        table4.addCell(professor);
        table4.addCell(motivo);

        document.add(table4);

        // **************************************************************************************
        
        document.add(new Paragraph("   "));
        // INICIO DA QUINTA TABELA:

        PdfPTable table5 = new PdfPTable(2);
        float[] columnWidths5 = {1f, 1f};
        table5.setWidths(columnWidths5);
        table5.setWidthPercentage(113);

        PdfPCell srProfessor = new PdfPCell(new Paragraph("Ao sr. professor:", fontBold));
        srProfessor.setPadding(5);
        PdfPCell funcionarioResponsavel = new PdfPCell(new Paragraph("Funcionário responsável:", fontBold));
        funcionarioResponsavel.setPadding(5);

        table5.addCell(srProfessor);
        table5.addCell(funcionarioResponsavel);

        document.add(table5);

        // ------------------------------------------------------------------------------------------------------//

        // INICIO DA SEXTA TABELA:

        PdfPTable table6 = new PdfPTable(3);
        float[] columnWidths6 = {1f, 1f, 1f};
        table6.setWidths(columnWidths6);
        table6.setWidthPercentage(113);

        PdfPCell data6 = new PdfPCell(new Paragraph("Data:", fontBold));
        data6.setPadding(5);
        PdfPCell dataRealizacao = new PdfPCell(new Paragraph("Data da realização da prova:", fontBold));
        dataRealizacao.setPadding(5);
        PdfPCell dataDivulgacaoNota = new PdfPCell(new Paragraph("Data da divulgação da nota:", fontBold));
        dataDivulgacaoNota.setPadding(5);

        table6.addCell(data6);
        table6.addCell(dataRealizacao);
        table6.addCell(dataDivulgacaoNota);
        document.add(table6);

        // ------------------------------------------------------------------------------------------------------//

        document.add(new Paragraph("   "));

        PdfPTable table7 = new PdfPTable(1);
        float[] columnWidths7 = {1f};
        table7.setWidths(columnWidths7);
        table7.setWidthPercentage(113);
        
        PdfPCell cell = new PdfPCell(new Paragraph("Avaliação do professor (os comentários poderão ser feitos no próprio corpo da prova ou à parte):", fontBold));
        cell.setBorder(Rectangle.BOX); // Mantém as bordas (ou você pode usar Rectangle.NO_BORDER para removê-las)
        cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Alinha o texto à esquerda
        cell.setVerticalAlignment(Element.ALIGN_TOP); // Alinha o texto no topo da célula
        cell.setMinimumHeight(80f); // Define a altura mínima da célula

        // Adiciona a célula à tabela
        table7.addCell(cell);
        document.add(table7);

        // ---------------------------------------------------------//

        //TABELA 8:

        PdfPTable table8 = new PdfPTable(3);
        float[] columnWidths8 = {1f, 1f, 1f};
        table8.setWidths(columnWidths8);
        table8.setWidthPercentage(113);

        PdfPCell conclusao = new PdfPCell(new Paragraph());
        conclusao.addElement(new Paragraph("Conclusao: \n", fontBold));
        conclusao.setRowspan(2);

        PdfPCell notaMantida = new PdfPCell(new Paragraph("Nota Mantida.", fontBold));
        notaMantida.setPadding(5);
        PdfPCell data = new PdfPCell(new Paragraph("Data:", fontBold));
        data.setPadding(5);
        PdfPCell notaAlterada = new PdfPCell(new Paragraph("Nota alterada para:", fontBold));
        notaAlterada.setPadding(5);
        PdfPCell professorResponsavel = new PdfPCell(new Paragraph("Professor responsável:", fontBold));
        professorResponsavel.setPadding(5);

        table8.addCell(conclusao);
        table8.addCell(notaMantida);
        table8.addCell(data);
        table8.addCell(notaAlterada);
        table8.addCell(professorResponsavel);


        document.add(table8);

        // ----------------------------------------------------------------------------------//
        document.add(new Paragraph("   "));

        //TABELA 9

        PdfPTable table9 = new PdfPTable(3);
        float[] columnWidths9 = {1f, 1f, 1f};
        table9.setWidths(columnWidths9);
        table9.setWidthPercentage(113);

        PdfPCell serdae = new PdfPCell(new Paragraph("Ao SERDAE:", fontBold));
        serdae.setPadding(5);
        PdfPCell deSerdae = new PdfPCell(new Paragraph("DE:", fontBold));
        deSerdae.setPadding(5);
        PdfPCell dataSerdae = new PdfPCell(new Paragraph("Data:", font10));
        dataSerdae.setPadding(5);
        PdfPCell ProvidenciarAlteracaoNota = new PdfPCell(new Paragraph("Providenciar alteração da nota.", font10));
        ProvidenciarAlteracaoNota.setPadding(5);
        PdfPCell paraSerdae = new PdfPCell(new Paragraph("PARA:", fontBold));
        paraSerdae.setPadding(5);
        PdfPCell FuncResponsavel = new PdfPCell(new Paragraph("Funcionário responsável:", font10));
        FuncResponsavel.setPadding(5);
        PdfPCell ou = new PdfPCell(new Paragraph("OU", fontBold));
        ou.setPadding(5);
        PdfPCell seac = new PdfPCell(new Paragraph("Ao SEAC:  ", fontBold));
        seac.setPadding(5);
        PdfPCell dataSeac = new PdfPCell(new Paragraph("Data:", font10));
        dataSeac.setPadding(5);
        PdfPCell indeferido = new PdfPCell(new Paragraph("Indeferido por estar fora do prazo.", font10));
        indeferido.setPadding(5);
        PdfPCell funcionarioResponsavelSeac = new PdfPCell(new Paragraph("Funcionário responsável:", font10));
        funcionarioResponsavelSeac.setPadding(5);

        ou.setColspan(3);
        ou.setHorizontalAlignment(Element.ALIGN_CENTER);
        seac.setColspan(2);
        indeferido.setColspan(2);

        table9.addCell(serdae);
        table9.addCell(deSerdae);
        table9.addCell(dataSerdae);
        table9.addCell(ProvidenciarAlteracaoNota);
        table9.addCell(paraSerdae);
        table9.addCell(FuncResponsavel);
        table9.addCell(ou);
        table9.addCell(seac);
        table9.addCell(dataSeac);
        table9.addCell(indeferido);
        table9.addCell(funcionarioResponsavelSeac);

        document.add(table9);

        // -------------------------------------------------------//

        //tabela 10

        document.add(new Paragraph("   "));

        PdfPTable table10 = new PdfPTable(2);
        float[] columnWidths10 = {1f, 1f};
        table10.setWidths(columnWidths10);
        table10.setWidthPercentage(113);

        PdfPCell seac2 = new PdfPCell(new Paragraph("Ao SEAC:  ", fontBold));
        seac2.setPadding(5);
        PdfPCell dataSeac2 = new PdfPCell(new Paragraph("Data:", font10));
        dataSeac2.setPadding(5);
        PdfPCell processadoConferido = new PdfPCell(new Paragraph("Processado e conferido.", font10));
        processadoConferido.setPadding(5);
        PdfPCell funcionarioResponsavelSeac2 = new PdfPCell(new Paragraph("Funcionário responsável:", font10));
        funcionarioResponsavelSeac2.setPadding(5);

        seac2.setColspan(2);
        processadoConferido.setColspan(2);

        table10.addCell(seac2);
        table10.addCell(dataSeac2);
        table10.addCell(processadoConferido);
        table10.addCell(funcionarioResponsavelSeac2);

        document.add(table10);

        // Fechar o documento
        document.close();

        // Retornar o conteúdo PDF gerado em um array de bytes
        return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }
}

