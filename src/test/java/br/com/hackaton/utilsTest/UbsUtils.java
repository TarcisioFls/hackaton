package br.com.hackaton.utilsTest;

import br.com.hackaton.controller.request.UbsRequest;
import br.com.hackaton.controller.response.UbsComMedicamentoResponse;
import br.com.hackaton.controller.response.UbsResponse;
import br.com.hackaton.entity.Ubs;

import java.util.List;

public class UbsUtils {

    private UbsUtils() {}

    public static UbsRequest buildUbsRequest() {
        return UbsRequest.builder()
                .nome("nome")
                .telefone("telefone")
                .endereco(EnderecoUtils.buildEnderecoRequest())
                .inicioAtendimento("inicioAtendimento")
                .fimAtendimento("fimAtendimento")
                .build();
    }

    public static UbsResponse buildUbsResponse() {
        return UbsResponse.builder()
                .id(1L)
                .nome("nome")
                .telefone("telefone")
                .endereco(EnderecoUtils.buildEnderecoResponse())
                .inicioAtendimento("inicioAtendimento")
                .fimAtendimento("fimAtendimento")
                .build();
    }

    public static UbsComMedicamentoResponse buildUbsComMedicamentoResponse() {
        return UbsComMedicamentoResponse.builder()
                .id(1L)
                .nome("nome")
                .telefone("telefone")
                .endereco(EnderecoUtils.buildEnderecoResponse())
                .inicioAtendimento("inicioAtendimento")
                .fimAtendimento("fimAtendimento")
                .distancia(1.0)
                .medicamento(MedicamentoUtils.buildMedicamentoResponse())
                .build();
    }

    public static Ubs buildUbs() {
        return Ubs.builder()
                .nome("nome")
                .telefone("telefone")
                .inicioAtendimento("inicioAtendimento")
                .fimAtendimento("fimAtendimento")
                .endereco(EnderecoUtils.buildEndereco())
                .estoque(List.of(EstoqueUtils.buildEstoque()))
                .build();
    }
}
