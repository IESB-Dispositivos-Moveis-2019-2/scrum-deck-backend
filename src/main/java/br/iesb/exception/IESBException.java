package br.iesb.exception;


import br.iesb.message.IMessageProperty;

import java.util.Optional;

public abstract class IESBException extends RuntimeException {
    private static final long serialVersionUID = -3485074360659570937L;

    private final IMessageProperty messageProperty;

    public Optional<IMessageProperty> getMessageField() {
        return Optional.ofNullable(messageProperty);
    }

    protected IESBException() {
        super();
        this.messageProperty = null;
    }

    protected IESBException(String message) {
        super(message);
        this.messageProperty = null;
    }

    protected IESBException(String message, Throwable cause) {
        super(message, cause);
        this.messageProperty = null;
    }

    protected IESBException(Throwable cause) {
        super(cause);
        this.messageProperty = null;
    }

    protected IESBException(IMessageProperty messageProperty) {
        super(messageProperty.message());
        this.messageProperty = messageProperty;
    }

    protected IESBException(String message, IMessageProperty messageProperty) {
        super(message);
        this.messageProperty = messageProperty;
    }

    protected IESBException(String message, Throwable cause, IMessageProperty messageProperty) {
        super(message, cause);
        this.messageProperty = messageProperty;
    }

    protected IESBException(IMessageProperty messageProperty, Throwable cause) {
        super(messageProperty.message(), cause);
        this.messageProperty = messageProperty;
    }
}
