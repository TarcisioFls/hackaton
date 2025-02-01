package br.com.hackaton.controller.request;

import br.com.hackaton.entity.Especialidade;
import jakarta.validation.constraints.NotNull;

public record MedicoRequest(
        @NotNull
        String nome,
        @NotNull
        String crm,
        @NotNull
        Especialidade especialidade
) {
}
