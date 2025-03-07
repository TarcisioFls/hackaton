package br.com.hackaton.controller.response;

import br.com.hackaton.entity.Tarja;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueUbsComQuantidadeResponse {

    private Long id;

    private Long medicamentoId;

    private String nome;

    private Tarja tarja;

    private String sku;

    private List<UbsComQuantidadeResponse> ubsComQuantidadeResponse;

}
