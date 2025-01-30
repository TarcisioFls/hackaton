package br.com.hackaton.controller.response;

import br.com.hackaton.entity.Medicamento;
import br.com.hackaton.entity.Tarja;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
public class MedicamentoResponse {

    private Long id;
    private String nome;
    private Tarja tarja;
    private String sku;
    private BigInteger quantidade;
    private LocalDateTime dataCriacao;

    public MedicamentoResponse() {}

    public MedicamentoResponse(Medicamento medicamento) {

        this.id = medicamento.getId();
        this.nome = medicamento.getNome();
        this.tarja = medicamento.getTarja();
        this.sku = medicamento.getSku();
        this.quantidade = medicamento.getQuantidade();
        this.dataCriacao = medicamento.getDataHoraCriacao();
    }
}
