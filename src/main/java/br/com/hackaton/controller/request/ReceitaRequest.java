package br.com.hackaton.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.With;

import java.util.List;

@With
@Builder
public record ReceitaRequest(

        @NotNull
        List<PosologiaRequest> posologias,

        @NotNull
        Long medicoId,

        @NotNull
        Long pacienteId
) {}
