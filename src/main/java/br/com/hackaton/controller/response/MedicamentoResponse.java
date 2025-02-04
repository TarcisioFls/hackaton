package br.com.hackaton.controller.response;

import br.com.hackaton.entity.Medicamento;
import br.com.hackaton.entity.Tarja;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MedicamentoResponse {

    private Long id;
    private String nome;
    private Tarja tarja;
    private String sku;
    private LocalDateTime dataCriacao;

    public MedicamentoResponse() {}

    public MedicamentoResponse(Medicamento medicamento) {

        this.id = medicamento.getId();
        this.nome = medicamento.getNome();
        this.tarja = medicamento.getTarja();
        this.sku = medicamento.getSku();
        this.dataCriacao = medicamento.getDataHoraCriacao();
    }

    public MedicamentoResponse(MedicamentoResponse medicamentoResponse) {

        this.id = medicamentoResponse.getId();
        this.nome = medicamentoResponse.getNome();
        this.tarja = medicamentoResponse.getTarja();
        this.sku = medicamentoResponse.getSku();
        this.dataCriacao = medicamentoResponse.getDataCriacao();
    }
}
