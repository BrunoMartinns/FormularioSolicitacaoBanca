package com.c3.formulario_banca.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;

@RestController
public class HomeController {

    @GetMapping("/")
      public ResponseEntity<Resource> home() {
      Resource resource = new ClassPathResource("static/formulario.html");
      return ResponseEntity.ok().body(resource);
    }
    
}
