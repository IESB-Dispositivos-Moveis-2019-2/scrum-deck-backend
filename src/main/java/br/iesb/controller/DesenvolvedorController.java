package br.iesb.controller;

import br.iesb.model.dto.entrada.CadastroDesenvolvedorDTO;
import br.iesb.model.dto.saida.DesenvolvedorDTO;
import br.iesb.service.DesenvolvedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.Email;

@RestController
@RequiredArgsConstructor
@RequestMapping("/desenvolvedor")
@Tag(name = "Desenvolvedor", description = "Operações sobre a entidade Desenvolvedor")
public class DesenvolvedorController {

    private final DesenvolvedorService service;

    @PostMapping
    @Operation(summary = "Cadastrar", description = "Cadastrar um novo Desenvolvedor")
    public ResponseEntity<DesenvolvedorDTO> cadastrar(@Validated @RequestBody CadastroDesenvolvedorDTO cadastroDesenvolvedorDTO) {
        final var dto = service.cadastrar(cadastroDesenvolvedorDTO);

        final var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(location).body(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperar", description = "Recuperar um Desenvolvedor por ID")
    public ResponseEntity<DesenvolvedorDTO> recuperar(@PathVariable Long id) {
        return ResponseEntity.ok(service.recuperar(id));
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Recuperar", description = "Recuperar um Desenvolvedor por E-mail")
    public ResponseEntity<DesenvolvedorDTO> recuperar(@PathVariable @Email String email) {
        return ResponseEntity.ok(service.recuperar(email));
    }

}
