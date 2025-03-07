package br.com.hackaton.controller.response;

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
public class EstoqueComMedicamentoComQuantidadeListResponse {

    private Long id;

    private Long ubsId;

    private String ubsNome;

    private String telefone;

    private String inicioAtendimento;

    private String fimAtendimento;

    private List<MedicamentoComQuantidadeResponse> medicamento;

    private EnderecoResponse endereco;

}
