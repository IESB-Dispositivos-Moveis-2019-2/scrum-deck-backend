package br.iesb.controller;

import br.iesb.model.dto.entrada.CadastroSprintDTO;
import br.iesb.model.dto.saida.SprintDTO;
import br.iesb.service.SprintService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sprint")
@Tag(name = "Sprint", description = "Operações sobre a entidade Sprint")
public class SprintController {

    private final SprintService service;

    @GetMapping
    @Operation(summary = "Recuperar", description = "Recuperar todas as Sprints cadastradas")
    public ResponseEntity<List<SprintDTO>> recuperarTodos() {
        return ResponseEntity.ok(service.recuperarTodos());
    }

    @PostMapping
    @Operation(summary = "Cadastrar", description = "Cadastrar uma nova Sprint")
    public ResponseEntity<SprintDTO> criarSprint(@Validated @RequestBody CadastroSprintDTO cadastroSprintDTO) {
        final var dto = service.cadastrar(cadastroSprintDTO);

        final var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(location).body(dto);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperar", description = "Recuperar uma Sprint por ID")
    public ResponseEntity<SprintDTO> recuperarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.recuperarPorId(id));
    }
}
