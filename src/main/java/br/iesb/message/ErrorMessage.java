package br.iesb.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private String error;
    private String message;

    public ErrorMessage(IMessageProperty messageProperty) {
        this.error = messageProperty.key();
        this.message = messageProperty.message();
    }


}
