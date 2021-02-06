package br.iesb.controller;

import br.iesb.model.dto.entrada.CadastroEstoriaDTO;
import br.iesb.model.dto.saida.EstoriaDTO;
import br.iesb.service.EstoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estoria")
@Tag(name = "Estoria", description = "Operações sobre a entidade Estoria")
public class EstoriaController {

    private final EstoriaService service;

    @PostMapping
    @Operation(summary = "Cadastrar", description = "Cadastrar uma nova Estória de Usuário")
    public ResponseEntity<EstoriaDTO> cadastrar(@Validated @RequestBody CadastroEstoriaDTO cadastroEstoriaDTO) {
        final var dto = service.cadastrar(cadastroEstoriaDTO);

        final var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(location).body(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperar", description = "Recuperar uma Estória por ID")
    public ResponseEntity<EstoriaDTO> recuperar(@PathVariable Long id) {
        return ResponseEntity.ok(service.recuperar(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir", description = "Excluir uma Estória por ID")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().cacheControl(CacheControl.noCache()).build();
    }

    @GetMapping("/sprint/{idSprint}")
    @Operation(summary = "Estórias da Sprint", description = "Recuperar a lista de estórias de uma sprint")
    public ResponseEntity<List<EstoriaDTO>> recuperarPorSprint(@PathVariable Long idSprint) {
        return ResponseEntity.ok(service.recuperarPorSprint(idSprint));
    }

    @PutMapping("/{id}/encerrar-votacao")
    @Operation(summary = "Encerrar Votação", description = "Encerra a coleta de votos de uma Estória")
    public ResponseEntity<Integer> encerrarVotacao(@PathVariable Long id) {
        return ResponseEntity.ok(service.encerrarVotacao(id));
    }

}
