package br.com.hackaton.controller.request;

import br.com.hackaton.entity.Tarja;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicamentoRequest(

        @NotBlank
        String nome,

        @NotNull
        Tarja tarja
) {}
