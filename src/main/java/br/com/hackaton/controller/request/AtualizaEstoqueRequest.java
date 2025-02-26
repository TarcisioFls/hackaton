package br.com.hackaton.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.With;

import java.math.BigInteger;

@With
@Builder
public record AtualizaEstoqueRequest(

        @NotNull
        BigInteger quantidade
) {}
