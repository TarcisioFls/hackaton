package br.com.hackaton.service.impl;

import br.com.hackaton.controller.request.AtualizaEstoqueRequest;
import br.com.hackaton.controller.request.EstoqueRequest;
import br.com.hackaton.controller.response.EnderecoResponse;
import br.com.hackaton.controller.response.EstoqueComMedicamentoComQuantidadeListResponse;
import br.com.hackaton.controller.response.EstoqueUbsComQuantidadeResponse;
import br.com.hackaton.controller.response.MedicamentoComQuantidadeResponse;
import br.com.hackaton.controller.response.UbsComQuantidadeResponse;
import br.com.hackaton.entity.Estoque;
import br.com.hackaton.exception.CodigoError;
import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.repository.EstoqueRepository;
import br.com.hackaton.service.EstoqueService;
import br.com.hackaton.service.MedicamentoService;
import br.com.hackaton.service.UbsService;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.hackaton.exception.CodigoError.ESTOQUE_NAO_ENCONTRADO;

@Service
public class EstoqueServiceImpl implements EstoqueService {

    private final EstoqueRepository estoqueRepository;

    private final MedicamentoService medicamentoService;

    private final UbsService ubsService;

    public EstoqueServiceImpl(EstoqueRepository estoqueRepository, MedicamentoService medicamentoService, UbsService ubsService) {
        this.estoqueRepository = estoqueRepository;
        this.medicamentoService = medicamentoService;
        this.ubsService = ubsService;
    }

    @Override
    public void criar(EstoqueRequest request) {

        var medicamentoResponse = medicamentoService.buscarPorId(request.medicamentoId());

        var ubsResponse = ubsService.buscarPorId(request.ubsId());

        var estoque = new Estoque(request.quantidade(), medicamentoResponse, ubsResponse);

        estoqueRepository.save(estoque);

    }

    @Override
    public void adicionar(Long id, AtualizaEstoqueRequest request) {

        var estoque = estoqueRepository.findById(id).orElseThrow(
                () -> new ExceptionAdvice(ESTOQUE_NAO_ENCONTRADO)
        );

        estoque.adiciona(request.quantidade());

        estoqueRepository.save(estoque);
    }

    @Override
    public void retirar(Long id, AtualizaEstoqueRequest request) {

        var estoque = estoqueRepository.findById(id).orElseThrow(
                () -> new ExceptionAdvice(ESTOQUE_NAO_ENCONTRADO)
        );

        estoque.retira(request.quantidade());

        estoqueRepository.save(estoque);
    }

    @Override
    public EstoqueComMedicamentoComQuantidadeListResponse buscaPorUbsId(Long ubsId) {

        var estoqueList = estoqueRepository.findByUbsId(ubsId).orElseThrow(
                () -> new ExceptionAdvice(CodigoError.ESTOQUE_NAO_ENCONTRADO)
        );

        var estoqueUbsResponse = getEstoqueUbsResponse(estoqueList);

        var medicamentoResponseList = getEstoqueMedicamentoResponses(estoqueList);

        estoqueUbsResponse.setMedicamento(medicamentoResponseList);

        return estoqueUbsResponse;
    }

    @Override
    public EstoqueUbsComQuantidadeResponse buscaPorMedicamentoId(Long medicamentoId) {

        var estoqueList = estoqueRepository.findByMedicamentoId(medicamentoId).orElseThrow(
                () -> new ExceptionAdvice(CodigoError.ESTOQUE_NAO_ENCONTRADO)
        );

        var estoqueUbsComQuantidadeResponse = getEstoqueUbsComQuantidadeResponse(estoqueList);

        var ubsComQuantidadeResponseList = getUbsComQuantidadeResponses(estoqueList);

        estoqueUbsComQuantidadeResponse.setUbsComQuantidadeResponse(ubsComQuantidadeResponseList);

        return estoqueUbsComQuantidadeResponse;
    }

    private static List<UbsComQuantidadeResponse> getUbsComQuantidadeResponses(List<Estoque> estoqueList) {

        return estoqueList.stream().map(estoque -> {
            UbsComQuantidadeResponse ubsComQuantidadeResponse = new UbsComQuantidadeResponse();
            ubsComQuantidadeResponse.setId(estoque.getUbs().getId());
            ubsComQuantidadeResponse.setNome(estoque.getUbs().getNome());
            ubsComQuantidadeResponse.setTelefone(estoque.getUbs().getTelefone());
            ubsComQuantidadeResponse.setInicioAtendimento(estoque.getUbs().getInicioAtendimento());
            ubsComQuantidadeResponse.setFimAtendimento(estoque.getUbs().getFimAtendimento());
            ubsComQuantidadeResponse.setQuantidade(estoque.getQuantidade());
            ubsComQuantidadeResponse.setEndereco(new EnderecoResponse(estoque.getUbs().getEndereco()));

            return ubsComQuantidadeResponse;
        }).toList();
    }

    private static EstoqueUbsComQuantidadeResponse getEstoqueUbsComQuantidadeResponse(List<Estoque> estoqueList) {

        var estoqueUbsComQuantidadeResponse = new EstoqueUbsComQuantidadeResponse();
        if (!estoqueList.isEmpty()) {
            estoqueUbsComQuantidadeResponse.setId(estoqueList.getFirst().getId());
            estoqueUbsComQuantidadeResponse.setMedicamentoId(estoqueList.getFirst().getMedicamento().getId());
            estoqueUbsComQuantidadeResponse.setNome(estoqueList.getFirst().getMedicamento().getNome());
            estoqueUbsComQuantidadeResponse.setTarja(estoqueList.getFirst().getMedicamento().getTarja());
            estoqueUbsComQuantidadeResponse.setSku(estoqueList.getFirst().getMedicamento().getSku());
        }

        return estoqueUbsComQuantidadeResponse;
    }

    private static List<MedicamentoComQuantidadeResponse> getEstoqueMedicamentoResponses(List<Estoque> estoqueList) {

        return estoqueList.stream().map(estoque -> {
            var estoqueMedicamentoResponse = new MedicamentoComQuantidadeResponse();
            estoqueMedicamentoResponse.setId(estoque.getMedicamento().getId());
            estoqueMedicamentoResponse.setNome(estoque.getMedicamento().getNome());
            estoqueMedicamentoResponse.setSku(estoque.getMedicamento().getSku());
            estoqueMedicamentoResponse.setTarja(estoque.getMedicamento().getTarja());
            estoqueMedicamentoResponse.setQuantidade(estoque.getQuantidade());

            return estoqueMedicamentoResponse;
        }).toList();
    }

    private EstoqueComMedicamentoComQuantidadeListResponse getEstoqueUbsResponse(List<Estoque> estoqueList) {

        var estoqueUbsResponse = new EstoqueComMedicamentoComQuantidadeListResponse();
        if (!estoqueList.isEmpty()) {
            estoqueUbsResponse.setId(estoqueList.getFirst().getId());
            estoqueUbsResponse.setUbsId(estoqueList.getFirst().getUbs().getId());
            estoqueUbsResponse.setUbsNome(estoqueList.getFirst().getUbs().getNome());
            estoqueUbsResponse.setTelefone(estoqueList.getFirst().getUbs().getTelefone());
            estoqueUbsResponse.setInicioAtendimento(estoqueList.getFirst().getUbs().getInicioAtendimento());
            estoqueUbsResponse.setFimAtendimento(estoqueList.getFirst().getUbs().getFimAtendimento());
            estoqueUbsResponse.setEndereco(new EnderecoResponse(estoqueList.getFirst().getUbs().getEndereco()));
        }

        return estoqueUbsResponse;
    }
}
