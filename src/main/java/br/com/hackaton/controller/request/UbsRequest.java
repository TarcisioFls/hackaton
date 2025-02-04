package br.com.hackaton.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UbsRequest(

        @NotBlank
        String nome,

        @NotBlank
        String telefone,

        @NotNull
        EnderecoRequest endereco,

        @NotBlank
        String inicioAtendimento,

        @NotBlank
        String fimAtendimento
) {}
