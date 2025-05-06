package com.ejemplo.div;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class OperacionController {

    @PostMapping("/div")
    public ResponseEntity<?> operar(@RequestBody Map<String, Object> body) {
        double a = Double.parseDouble(body.get("a").toString());
        double b = Double.parseDouble(body.get("b").toString());
        if (b == 0) return ResponseEntity.badRequest().body(Map.of("error", "División entre cero"));
        return ResponseEntity.ok(Map.of("resultado", a / b));
    }
}