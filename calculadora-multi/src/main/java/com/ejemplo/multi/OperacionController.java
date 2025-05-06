package com.ejemplo.multi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class OperacionController {

    @PostMapping("/multi")
    public ResponseEntity<?> operar(@RequestBody Map<String, Object> body) {
        double a = Double.parseDouble(body.get("a").toString());
        double b = Double.parseDouble(body.get("b").toString());
        return ResponseEntity.ok(Map.of("resultado", a * b));
    }
}