package com.ejemplo.orquestador;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class OperacionOrquestadorController {

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/calc")
    public ResponseEntity<?> calcular(@RequestBody Map<String, Object> body) {
        String op = body.get("op").toString();
        double a = Double.parseDouble(body.get("a").toString());
        double b = Double.parseDouble(body.get("b").toString());

        String url = switch (op) {
            case "suma" -> "http://localhost:8081/suma";
            case "resta" -> "http://localhost:8082/resta";
            case "multi" -> "http://localhost:8083/multi";
            case "div" -> "http://localhost:8084/div";
            default -> null;
        };

        if (url == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Operación no válida"));
        }

        try {
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(Map.of("a", a, "b", b));
            return restTemplate.exchange(url, HttpMethod.POST, request, Map.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }
}