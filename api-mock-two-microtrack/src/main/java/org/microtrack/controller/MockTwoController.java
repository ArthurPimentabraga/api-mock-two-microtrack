package org.microtrack.controller;

import jakarta.validation.Valid;
import org.microtrack.model.dto.MockTwoBodyDTO;
import org.microtrack.service.MockTwoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/mock-two")
public class MockTwoController {

    public final MockTwoService service = new MockTwoService();

    @PostMapping
    public ResponseEntity<String> testOne(@RequestBody @Valid MockTwoBodyDTO body) throws IOException, InterruptedException {
        service.test(body);
        return ResponseEntity.ok("Deu certo!");
    }

}
