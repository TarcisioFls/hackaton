package br.com.hackaton.controller.response;

import br.com.hackaton.entity.Tarja;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class MedicamentoComQuantidadeResponse {

    private Long id;

    private String nome;

    private Tarja tarja;

    private String sku;

    private BigInteger quantidade;

    public MedicamentoComQuantidadeResponse() {}

    public MedicamentoComQuantidadeResponse(Long id, String nome, Tarja tarja, String sku, BigInteger quantidade) {

        this.id = id;
        this.nome = nome;
        this.tarja = tarja;
        this.sku = sku;
        this.quantidade = quantidade;
    }

}
