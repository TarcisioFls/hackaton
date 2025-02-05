package br.com.hackaton.controller.response;

import br.com.hackaton.entity.Posologia;
import lombok.Getter;

import java.util.List;

@Getter
public class PosologiaResponse {

    private String descricao;

    private Integer quantidade;

    private MedicamentoResponse medicamento;

    public PosologiaResponse() {}

    public PosologiaResponse(String descricao, Integer quantidade, MedicamentoResponse medicamento) {

        this.descricao = descricao;
        this.quantidade = quantidade;
        this.medicamento = medicamento;

    }

    public List<PosologiaResponse> getPosologiaResponseLis(List<Posologia> posologias) {

        return posologias.stream().map(posologia -> new PosologiaResponse(
                posologia.getDescricao(),
                posologia.getQuantidade(),
                new MedicamentoResponse(posologia.getMedicamento())
        )).toList();
    }
}
