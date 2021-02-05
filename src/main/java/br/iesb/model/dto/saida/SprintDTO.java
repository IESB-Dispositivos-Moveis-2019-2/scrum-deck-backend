package br.iesb.model.dto.saida;

import lombok.Data;

import java.io.Serializable;

@Data
public class SprintDTO implements Serializable {
    private static final long serialVersionUID = -513298450358579438L;

    private Long id;
    private String nome;
    private String link;
}
