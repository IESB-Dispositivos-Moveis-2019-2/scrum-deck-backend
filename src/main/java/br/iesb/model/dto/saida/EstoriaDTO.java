package br.iesb.model.dto.saida;

import lombok.Data;

import java.io.Serializable;

@Data
public class EstoriaDTO implements Serializable {

    private Long id;
    private Long idSprint;
    private String nome;
    private String link;
    private Integer pontuacao;

}
