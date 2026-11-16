package br.com.aula.fullstack.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/calc")
public class CalculadoraController {

    @GetMapping("/soma/{a}/{b}")
    public Map<String, Double> somar(@PathVariable double a, @PathVariable double b) {
        return Map.of("resultado", a + b);
    }

    // Rotas alternativas para devolver 400 quando um ou os dois valores não forem informados.
    @GetMapping("/soma")
    public ResponseEntity<Map<String, String>> semParametros() {
        return ResponseEntity.badRequest().body(
                Map.of("erro", "Informe os dois parâmetros: a e b."));
    }

    @GetMapping("/soma/{a}")
    public ResponseEntity<Map<String, String>> faltaSegundoParametro(@PathVariable String a) {
        return ResponseEntity.badRequest().body(
                Map.of("erro", "Parâmetro ausente: informe também o valor b."));
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Map<String, String>> numeroInvalido() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("erro", "Os parâmetros a e b devem ser números."));
    }
}
