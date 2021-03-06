package br.iesb.model.dto.entrada;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class CadastroSprintDTO implements Serializable {
    private static final long serialVersionUID = 6789701060243297494L;

    @NotEmpty
    @Size(max = 100)
    private String nome;

    @URL
    @NotEmpty
    @Size(max = 255)
    private String link;
}
