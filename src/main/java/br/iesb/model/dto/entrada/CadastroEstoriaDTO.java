package br.iesb.model.dto.entrada;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class CadastroEstoriaDTO implements Serializable {
    private static final long serialVersionUID = 5051769689660930268L;

    @NotNull
    private Long idSprint;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String nome;

    @URL
    @NotEmpty
    @Size(max = 255)
    private String link;

}
