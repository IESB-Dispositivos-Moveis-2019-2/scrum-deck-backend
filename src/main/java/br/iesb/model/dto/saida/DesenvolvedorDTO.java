package br.iesb.model.dto.saida;

import lombok.Data;

import java.io.Serializable;

@Data
public class DesenvolvedorDTO implements Serializable {
    private static final long serialVersionUID = -1910420246013486932L;

    private Long id;
    private String nome;
    private String email;

}
