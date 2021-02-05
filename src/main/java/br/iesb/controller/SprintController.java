package br.iesb.controller;

import br.iesb.service.SprintService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sprint")
@Tag(name = "Sprint", description = "Operações sobre a entidade Sprint")
public class SprintController {

    private final SprintService service;

    @GetMapping
    private ResponseEntity<Void> teste() {
        service.teste();
        return ResponseEntity.noContent().build();
    }
}
