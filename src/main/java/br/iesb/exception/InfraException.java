package br.iesb.exception;

import br.iesb.message.IMessageProperty;

public class InfraException extends IESBException {
    private static final long serialVersionUID = 7573649955339961375L;

    public InfraException() {
        super();
    }

    public InfraException(String message) {
        super(message);
    }

    public InfraException(String message, Throwable cause) {
        super(message, cause);
    }

    public InfraException(Throwable cause) {
        super(cause);
    }

    public InfraException(IMessageProperty messageProperty) {
        super(messageProperty);
    }

    public InfraException(String message, IMessageProperty messageProperty) {
        super(message, messageProperty);
    }

    public InfraException(String message, Throwable cause, IMessageProperty messageProperty) {
        super(message, cause, messageProperty);
    }

    public InfraException(IMessageProperty messageProperty, Throwable cause) {
        super(messageProperty, cause);
    }
}
