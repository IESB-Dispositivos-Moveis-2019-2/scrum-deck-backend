package br.iesb.controller;

import br.iesb.model.dto.entrada.CadastroVotoDTO;
import br.iesb.model.dto.saida.VotoDTO;
import br.iesb.service.VotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/voto")
@Tag(name = "Voto", description = "Operações sobre a entidade Voto")
public class VotoController {

    private final VotoService service;


    @PostMapping
    @Operation(summary = "Cadastrar", description = "Registra um voto em uma Estória")
    public ResponseEntity<VotoDTO> cadastrar(@Validated @RequestBody CadastroVotoDTO cadastroVotoDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.cadastrar(cadastroVotoDTO));
    }

}
