package br.com.hackaton.controller.request;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRequest(

        String cep,

        String logradouro,

        String numero,

        String complemento,

        @NotBlank
        String bairro,

        @NotBlank
        String cidade,

        @NotBlank
        String estado,

        @NotBlank
        Double latitude,

        @NotBlank
        Double longitude
) {}
