package br.com.hackaton.controller.response;

import br.com.hackaton.entity.Tarja;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EstoqueUbsComQuantidadeResponse {

    private Long id;

    private Long medicamentoId;

    private String nome;

    private Tarja tarja;

    private String sku;

    private List<UbsComQuantidadeResponse> ubsComQuantidadeResponse;

}
