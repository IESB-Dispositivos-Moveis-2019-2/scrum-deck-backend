package br.iesb.model.dto.saida;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class VotoDTO implements Serializable {
    private static final long serialVersionUID = -812437335435743308L;

    private Long idEstoria;
    private Long idDesenvolvedor;
    private Integer pontos;
    private LocalDateTime dataHora;

}
