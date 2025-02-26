package br.com.hackaton.utils;

import br.com.hackaton.controller.request.AtualizaEstoqueRequest;
import br.com.hackaton.controller.request.EstoqueRequest;
import br.com.hackaton.controller.response.EnderecoResponse;
import br.com.hackaton.controller.response.EstoqueComMedicamentoComQuantidadeListResponse;
import br.com.hackaton.controller.response.EstoqueUbsComQuantidadeResponse;
import br.com.hackaton.controller.response.MedicamentoComQuantidadeResponse;
import br.com.hackaton.controller.response.UbsComQuantidadeResponse;
import br.com.hackaton.entity.Tarja;

import java.math.BigInteger;
import java.util.List;

public class EstoqueUtils {

    private EstoqueUtils() {}

    public static EstoqueRequest buildEstoqueRequest() {
        return EstoqueRequest.builder()
                .medicamentoId(1L)
                .ubsId(1L)
                .quantidade(BigInteger.ONE)
                .build();
    }

    public static AtualizaEstoqueRequest buildAtualizaEstoqueRequest() {
        return AtualizaEstoqueRequest.builder()
                .quantidade(BigInteger.ONE)
                .build();
    }

    public static EstoqueComMedicamentoComQuantidadeListResponse buildEstoqueComMedicamentoComQuantidadeListResponse() {
        return EstoqueComMedicamentoComQuantidadeListResponse.builder()
                .id(1L)
                .ubsId(1L)
                .ubsNome("UBS")
                .telefone("123456789")
                .inicioAtendimento("08:00")
                .fimAtendimento("17:00")
                .medicamento(List.of(buildMedicamentoComQuantidadeResponse()))
                .endereco(EnderecoUtils.buildEnderecoResponse())
                .build();
    }

    public static MedicamentoComQuantidadeResponse buildMedicamentoComQuantidadeResponse() {
        return MedicamentoComQuantidadeResponse.builder()
                .id(1L)
                .nome("Medicamento")
                .tarja(Tarja.PRETA)
                .sku("123456")
                .quantidade(BigInteger.ONE)
                .build();
    }

    public static EstoqueUbsComQuantidadeResponse buildEstoqueUbsComQuantidadeResponse() {
        return EstoqueUbsComQuantidadeResponse.builder()
                .id(1L)
                .medicamentoId(1L)
                .nome("Medicamento")
                .tarja(Tarja.PRETA)
                .sku("123456")
                .ubsComQuantidadeResponse(List.of(buildUbsComQuantidadeResponse()))
                .build();
    }

    public static UbsComQuantidadeResponse buildUbsComQuantidadeResponse() {
        return UbsComQuantidadeResponse.builder()
                .id(1L)
                .nome("UBS")
                .telefone("123456789")
                .inicioAtendimento("08:00")
                .fimAtendimento("17:00")
                .quantidade(BigInteger.ONE)
                .endereco(EnderecoUtils.buildEnderecoResponse())
                .build();
    }



}
