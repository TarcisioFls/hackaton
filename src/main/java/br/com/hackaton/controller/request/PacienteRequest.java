package br.com.hackaton.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record PacienteRequest(

        @NotBlank
        String nome,

        @NotBlank
        String email,

        @NotBlank
        String cpf,

        @NotBlank
        String telefone,

        @NotBlank
        String cns,

        @NotNull
        EnderecoRequest enderecoRequest
) {}
