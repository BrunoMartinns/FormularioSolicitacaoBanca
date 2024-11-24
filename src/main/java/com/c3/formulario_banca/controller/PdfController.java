package com.c3.formulario_banca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.c3.formulario_banca.entidades.FormularioDTO;
import com.c3.formulario_banca.service.FormularioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gerar-pdf")
@RequiredArgsConstructor
public class PdfController {

    @Autowired
    private final FormularioService formularioService;

    @PostMapping
    public ResponseEntity<byte[]> gerarPdf(@RequestBody FormularioDTO formulario) {
        byte[] pdf = formularioService.gerarPdf(formulario);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=formulario.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
