package org.microtrack.controller;

import jakarta.validation.Valid;
import org.microtrack.model.dto.ProductDTO;
import org.microtrack.service.MockTwoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/calculate-payment")
public class MockTwoController {

    public final MockTwoService service = new MockTwoService();

    @PostMapping
    public ResponseEntity<String> testOne(@RequestBody @Valid ProductDTO body) throws IOException, InterruptedException {
        try {
            service.calculateTotalPayment(body);
            return ResponseEntity.ok("Deu certo!");
        } catch (Exception ex) {
            // TODO add print
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

}
