package br.com.hackaton.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {

    private HttpStatus httpStatus;

    private String mensagem;

    private int codigo;

    public ErrorResponse(HttpStatus httpStatus, String mensagem) {
        this.httpStatus = httpStatus;
        this.mensagem = mensagem;
        this.codigo = httpStatus.value();
    }
}
