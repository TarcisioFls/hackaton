package br.com.hackaton.utils;

import br.com.hackaton.controller.request.EnderecoRequest;
import br.com.hackaton.controller.response.EnderecoResponse;

public class EnderecoUtils {

    private EnderecoUtils() {}



    public static EnderecoResponse buildEnderecoResponse() {
        return EnderecoResponse.builder()
                .cep("12345678")
                .logradouro("Rua")
                .numero("123")
                .complemento("Complemento")
                .bairro("Bairro")
                .cidade("Cidade")
                .estado("Estado")
                .latitude(1.0)
                .longitude(1.0)
                .build();
    }

    public static EnderecoRequest buildEnderecoRequest() {
        return EnderecoRequest.builder()
                .cep("12345678")
                .logradouro("Rua")
                .numero("123")
                .complemento("Complemento")
                .bairro("Bairro")
                .cidade("Cidade")
                .estado("Estado")
                .latitude(1.0)
                .longitude(1.0)
                .build();
    }
}
