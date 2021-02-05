package br.iesb.exception;


import br.iesb.message.IMessageProperty;

public class AccessForbiddenException extends IESBException {
    private static final long serialVersionUID = 9140369879016050191L;

    public AccessForbiddenException() {
        super();
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    public AccessForbiddenException(IMessageProperty messageProperty) {
        super(messageProperty);
    }

    public AccessForbiddenException(String message, IMessageProperty messageProperty) {
        super(message, messageProperty);
    }

    public AccessForbiddenException(String message, Throwable cause, IMessageProperty messageProperty) {
        super(message, cause, messageProperty);
    }

    public AccessForbiddenException(Throwable cause, IMessageProperty messageProperty) {
        super(messageProperty, cause);
    }
}
