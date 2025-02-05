package br.com.hackaton.controller.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ReceitaRequest(

        @NotNull
        List<PosologiaRequest> posologias,

        @NotNull
        Long medicoId,

        @NotNull
        Long pacienteId
) {}
