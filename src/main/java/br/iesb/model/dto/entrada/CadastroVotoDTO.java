package br.iesb.model.dto.entrada;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Data
public class CadastroVotoDTO implements Serializable {
    private static final long serialVersionUID = 2072557420523535754L;

    @NotNull
    private Long idEstoria;
    @NotNull
    private Long idDesenvolvedor;

    @NotNull
    @PositiveOrZero
    private Integer pontos;

}
