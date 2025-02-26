package br.com.hackaton.controller.response;

import br.com.hackaton.entity.Medicamento;
import br.com.hackaton.entity.Tarja;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoResponse {

    private Long id;
    private String nome;
    private Tarja tarja;
    private String sku;
    private LocalDateTime dataCriacao;

    public MedicamentoResponse(Medicamento medicamento) {

        this.id = medicamento.getId();
        this.nome = medicamento.getNome();
        this.tarja = medicamento.getTarja();
        this.sku = medicamento.getSku();
        this.dataCriacao = medicamento.getDataHoraCriacao();
    }
}
