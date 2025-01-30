package br.com.hackaton.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionAdvice extends RuntimeException {

    private final CodigoError codigoError;

    public ExceptionAdvice(CodigoError codigoError) {
        super(codigoError.getMensagem());
        this.codigoError = codigoError;
    }

    public HttpStatus getHttpStatus() {
        return codigoError.getHttpStatus();
    }
}
