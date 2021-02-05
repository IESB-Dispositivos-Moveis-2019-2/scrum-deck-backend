package br.iesb.exception;


import br.iesb.message.IMessageProperty;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BusinessException extends IESBException {

    private final List<IMessageProperty> messages = new ArrayList<>();

    /**
     * @deprecated Este construtor não fornece uma mensagem de erro
     * @see IMessageProperty#businessException(Throwable)
     */
    @Deprecated
    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(IMessageProperty message) {
        super(message);
        this.messages.add(message);
    }

    public BusinessException(IMessageProperty... pMessages) {
        super(ArrayUtils.isNotEmpty(pMessages) ?Arrays.stream(pMessages).map(IMessageProperty::message).collect(Collectors.joining()) : StringUtils.EMPTY);
        this.messages.addAll(Arrays.asList(pMessages));
    }

    public BusinessException(IMessageProperty message, Throwable cause) {
        super(message, cause);
        this.messages.add(message);
    }

}
