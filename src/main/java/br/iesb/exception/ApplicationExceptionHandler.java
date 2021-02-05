package br.iesb.exception;

import br.iesb.message.CoreMessageProperty;
import br.iesb.message.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> handleIesbException(IESBException ex, WebRequest request, HttpStatus status, CoreMessageProperty defaultMessage) {
        final var msg = ex.getMessageField().orElse(defaultMessage);
        final var erros = Collections.singletonList(new ErrorMessage(msg));
        log.debug(ex.getMessage(), ex);
        return handleExceptionInternal(ex, erros, new HttpHeaders(), status, request);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessForbiddenException.class)
    protected ResponseEntity<Object> handleAccessForbidden(AccessForbiddenException ex, WebRequest request) {
        return handleIesbException(ex, request, HttpStatus.FORBIDDEN, CoreMessageProperty.API_ACCESS_FORBIDDEN);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusiness(BusinessException ex, WebRequest request) {
        final var erros = new ArrayList<>();
        log.debug(ex.getMessage(), ex);
        if (ex.getMessages().isEmpty()) {
            erros.add(new ErrorMessage("BusinessException", Optional.ofNullable(ex.getMessage()).orElse(StringUtils.EMPTY)));
        } else {
            ex.getMessages().iterator().forEachRemaining(msg -> erros.add(new ErrorMessage(msg)));
            if (erros.isEmpty()) {
                erros.add(new ErrorMessage(CoreMessageProperty.API_UNDENTIFIED_ERROR));
            }
        }
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        return handleIesbException(ex, request, HttpStatus.NOT_FOUND, CoreMessageProperty.API_RESOURCE_NOTFOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InfraException.class)
    protected ResponseEntity<Object> handleInfra(InfraException ex, WebRequest request) {
        return handleIesbException(ex, request, HttpStatus.INTERNAL_SERVER_ERROR, CoreMessageProperty.API_UNDENTIFIED_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final var erros = new ArrayList<ErrorMessage>();
        if (!CollectionUtils.isEmpty(ex.getBindingResult().getFieldErrors())) {
            erros.addAll(ex.getBindingResult().getFieldErrors().parallelStream()
                .map(fe -> new ErrorMessage(String.format("%s.%s", fe.getObjectName(), fe.getField()), fe.getDefaultMessage()))
                .collect(Collectors.toList()));
        }else {
            erros.add(new ErrorMessage(ex.getParameter().getParameterName(), ex.getBindingResult().toString()));
        }
        log.debug(ex.getMessage(), ex);
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final var erros = Collections.singletonList(new ErrorMessage(ex.getMessage(), "Tipos suportados: " + MediaType.toString(ex.getSupportedMediaTypes())));
        log.debug(ex.getMessage(), ex);
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
