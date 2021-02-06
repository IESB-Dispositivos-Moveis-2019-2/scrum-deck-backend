package br.iesb.model.dto.entrada;

import lombok.Data;

import java.io.Serializable;

@Data
public class CadastroEstoriaDTO implements Serializable {

    private Long idSprint;
    private String nome;
    private String link;

}
