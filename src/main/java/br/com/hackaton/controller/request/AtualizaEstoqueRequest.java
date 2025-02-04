package br.com.hackaton.controller.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;

public record AtualizaEstoqueRequest(

        @NotNull
        BigInteger quantidade
) {}
