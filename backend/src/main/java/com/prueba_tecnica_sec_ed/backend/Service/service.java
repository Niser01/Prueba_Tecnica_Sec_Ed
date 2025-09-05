package com.prueba_tecnica_sec_ed.backend.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class service {

    //Se definen las posibilidades en texto de los numero para la salida
    private static final String[] EXIT_NUMBERS = {
        "zero", "one", "two", "three", "four", "five", "six", "seven", 
        "eight", "nine", "ten", "eleven", "twelve", "thirteen", 
        "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", 
        "nineteen", "twenty", "twenty one", "twenty two", "twenty three", 
        "twenty four", "twenty five", "twenty six", "twenty seven", 
        "twenty eight", "twenty nine"
    };

    private String hora_en_palabras(int hora, int minuto){
        if (minuto == 0){
            return EXIT_NUMBERS[hora] + "o' clock";
        }
        if (minuto == 15){
            return "quarter past " + EXIT_NUMBERS[hora];
        }
        if (minuto == 30){
            return "half past " + EXIT_NUMBERS[hora];
        }
        if (minuto == 45){
            return "quarter to " + EXIT_NUMBERS[(hora%12)+1];
        }
        if (minuto < 30){
            return (minuto == 1 ? EXIT_NUMBERS[minuto] + " minute" : EXIT_NUMBERS[minuto] + " minutes") + " past " + EXIT_NUMBERS[hora];
        }
        
        int restantes = 60-minuto;
        return(restantes == 1 ? EXIT_NUMBERS[restantes]+ "minute" : EXIT_NUMBERS[restantes]+" minutes")+" to " + EXIT_NUMBERS[(hora % 12) + 1];
    }
    
    public List<Map<String, Object>> process_file(MultipartFile file){

        //Se valida que se este cargando un archivo y que este contenga informacion
        if (file == null || file.isEmpty()){
            throw new IllegalArgumentException("No se encontro un archivo para el programa");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))){
            List<String> lines = reader.lines().map(String::trim).collect(Collectors.toList());
            
            //Se valida que el archivo contenga un numero par de lineas ya que est respetaria la condicion de 
            //H
            //MM
            if(lines.size()%2 !=0){
                throw new IllegalArgumentException("El archivo no contiene los datos necesarios para ser procesado");
            }

            List<Map<String, Object>> results = new ArrayList<>();

            for(int i = 0; i< lines.size(); i +=2){
                int hora = 0;
                int minutos = 0;
                //Se valida la consistencia de los valores ingresados
                try{
                    hora = Integer.parseInt(lines.get(i));
                }catch(Exception e){
                    throw new IllegalArgumentException(String.format("Valor inválido para hora: '%s'. Debe ser un número entero.", lines.get(i)));
                }
                try{
                    minutos = Integer.parseInt(lines.get(i+1));
                }catch(Exception e){
                    throw new IllegalArgumentException(String.format("Valor inválido para los minutos: '%s'. Debe ser un número entero.", lines.get(i+1)));
                }
                
                //Se valida que la informacion obtenida este dentro de los rangos permitidos por el problema
                if (hora < 1 || hora > 12){
                    throw new IllegalArgumentException("Hora fuera de rango para: " + hora);
                }
                if (minutos < 0 || minutos > 59){
                    throw new IllegalArgumentException("Minutos fuera de rango para: " + minutos);
                }

                //Se hace la conversion de la hora a texto
                String numero_a_texto = hora_en_palabras(hora, minutos);

                Map<String, Object> resultado = new HashMap<>();
                resultado.put("hora ", hora);
                resultado.put("minutos", String.format("%02d", minutos));
                resultado.put("resultado", numero_a_texto);
                results.add(resultado);
            }
            return results;

        }catch(IOException e){
            throw new RuntimeException("Error al leer el archivo.", e);
        }
    }
}
