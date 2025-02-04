package br.com.hackaton.controller.request;

import br.com.hackaton.entity.Especialidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MedicoRequest(

        @NotBlank
        String nome,

        @NotBlank
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        String crm,

        @NotNull
        List<Especialidade> especialidades
) {
}
