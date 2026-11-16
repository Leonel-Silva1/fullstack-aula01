package br.com.aula.fullstack.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/temperatura")
public class TemperaturaController {

    private static final Set<String> ESCALAS_VALIDAS = Set.of("C", "F", "K");

    @GetMapping("/{valor}/{de}/{para}")
    public Map<String, Object> converter(
            @PathVariable double valor,
            @PathVariable String de,
            @PathVariable String para) {

        String origem = de.toUpperCase();
        String destino = para.toUpperCase();

        if (!ESCALAS_VALIDAS.contains(origem) || !ESCALAS_VALIDAS.contains(destino)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Escala inválida. Utilize somente C, F ou K em 'de' e 'para'.");
        }

        double celsius = switch (origem) {
            case "C" -> valor;
            case "F" -> (valor - 32) / 1.8;
            case "K" -> valor - 273.15;
            default -> throw new IllegalStateException("Escala não suportada");
        };

        double resultado = switch (destino) {
            case "C" -> celsius;
            case "F" -> (celsius * 1.8) + 32;
            case "K" -> celsius + 273.15;
            default -> throw new IllegalStateException("Escala não suportada");
        };

        return Map.of(
                "valorOriginal", valor,
                "de", origem,
                "para", destino,
                "resultado", Math.round(resultado * 100.0) / 100.0
        );
    }
}
