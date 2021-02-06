package br.iesb.model.dto.entrada;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class CadastroDesenvolvedorDTO implements Serializable {
    private static final long serialVersionUID = 4832403024173986245L;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String nome;

    @Email
    @NotEmpty
    @Size(max = 255)
    private String email;

}
