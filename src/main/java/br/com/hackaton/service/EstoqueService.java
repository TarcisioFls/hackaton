package br.com.hackaton.service;

import br.com.hackaton.controller.request.AtualizaEstoqueRequest;
import br.com.hackaton.controller.request.EstoqueRequest;
import br.com.hackaton.controller.response.EstoqueComMedicamentoComQuantidadeListResponse;
import br.com.hackaton.controller.response.EstoqueUbsComQuantidadeResponse;

public interface EstoqueService {

    void cria(EstoqueRequest request);

    void adiciona(Long id, AtualizaEstoqueRequest request);

    void retira(Long id, AtualizaEstoqueRequest request);

    EstoqueComMedicamentoComQuantidadeListResponse buscaPorUbsId(Long ubsId);

    EstoqueUbsComQuantidadeResponse buscaPorMedicamentoId(Long medicamentoId);
}
