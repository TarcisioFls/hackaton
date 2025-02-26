package br.com.hackaton.controller.request;

import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record PosologiaRequest(

        @NotNull
        Long medicamentoId,

        @Negative
        @NotNull
        Integer quantidade,

        @NotBlank
        String descricao
) {}
