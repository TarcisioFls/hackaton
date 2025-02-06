package br.com.hackaton.service;

import br.com.hackaton.controller.request.AtualizaEstoqueRequest;
import br.com.hackaton.controller.request.EstoqueRequest;
import br.com.hackaton.controller.response.EstoqueComMedicamentoComQuantidadeListResponse;
import br.com.hackaton.controller.response.EstoqueUbsComQuantidadeResponse;

public interface EstoqueService {

    void criar(EstoqueRequest request);

    void adicionar(Long id, AtualizaEstoqueRequest request);

    void retirar(Long id, AtualizaEstoqueRequest request);

    EstoqueComMedicamentoComQuantidadeListResponse buscaPorUbsId(Long ubsId);

    EstoqueUbsComQuantidadeResponse buscaPorMedicamentoId(Long medicamentoId);
}
