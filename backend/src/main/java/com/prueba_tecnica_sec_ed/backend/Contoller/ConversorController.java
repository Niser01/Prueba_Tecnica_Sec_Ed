package com.prueba_tecnica_sec_ed.backend.Contoller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.prueba_tecnica_sec_ed.backend.Service.ConversorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;



@RestController
public class ConversorController {
    
    //Se hace el proceso de inyeccion del servicio al controlador
    private final ConversorService conversorService;

    public ConversorController (ConversorService conversorService){
        this.conversorService = conversorService;
    }

    //Se realiza el mapeo de endpoint y se indica que se cargara un archivo y devolvera un json
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        //Se mapea el campo donde se recibira el archivo  y se genera un try catch donde se le dice al servicio que procese el archivo recibido 
        //Si no hay errores que muestre el resultado de lo contrario se maneja el carch para mostrar el error
        try {
            List<Map<String, Object>> resultados = conversorService.process_file(file);
            return ResponseEntity.ok(resultados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }
}
