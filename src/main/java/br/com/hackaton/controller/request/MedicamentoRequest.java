package br.com.hackaton.controller.request;

import br.com.hackaton.entity.Tarja;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record MedicamentoRequest(

        @NotBlank
        String nome,

        @NotNull
        Tarja tarja
) {}
