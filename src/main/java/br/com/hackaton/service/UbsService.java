package br.com.hackaton.service;

import br.com.hackaton.controller.request.UbsRequest;
import br.com.hackaton.controller.response.UbsComMedicamentoResponse;
import br.com.hackaton.controller.response.UbsResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UbsService {

    void cria(UbsRequest request);

    UbsResponse buscarPorId(Long id);

    UbsResponse atualiza(Long id, UbsRequest request);

    Page<UbsResponse> buscarTodos(int page, int size);

    List<UbsComMedicamentoResponse> encontrarUbsProximasDePacienteComMedicamentos(Long receitaId);

    void enviarEmailUbsProximasDePacienteComMedicamentos(Long receitaId);
}
