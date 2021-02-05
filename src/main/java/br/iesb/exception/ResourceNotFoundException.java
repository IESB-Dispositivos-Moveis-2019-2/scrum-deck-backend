package br.iesb.exception;


import br.iesb.message.IMessageProperty;

public class ResourceNotFoundException extends IESBException {
    private static final long serialVersionUID = 8351575330036082791L;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResourceNotFoundException(IMessageProperty messageProperty) {
        super(messageProperty);
    }

    public ResourceNotFoundException(String message, IMessageProperty messageProperty) {
        super(message, messageProperty);
    }

    public ResourceNotFoundException(String message, Throwable cause, IMessageProperty messageProperty) {
        super(message, cause, messageProperty);
    }

    public ResourceNotFoundException(IMessageProperty messageProperty, Throwable cause) {
        super(messageProperty, cause);
    }
}
