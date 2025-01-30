package br.com.hackaton.controller.request;

import br.com.hackaton.entity.Tarja;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;

public record MedicamentoRequest(
        @NotBlank
        String nome,
        @NotNull
        @Negative
        BigInteger quantidade,
        @NotNull
        Tarja tarja
) {}
