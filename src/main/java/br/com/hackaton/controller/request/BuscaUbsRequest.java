package br.com.hackaton.controller.request;

import java.math.BigInteger;
import java.util.List;

public record BuscaUbsRequest(
        double latitude,
        double longitude,
        List<MedicamentoReceita> medicamentos
) {
    public record MedicamentoReceita(
            String nome,
            BigInteger quantidade
    ) {}
}