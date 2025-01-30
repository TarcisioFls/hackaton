package br.com.hackaton.service;

import br.com.hackaton.controller.request.MedicamentoRequest;
import br.com.hackaton.controller.response.MedicamentoResponse;
import org.springframework.data.domain.Page;

public interface MedicamentoService {

    void cria(MedicamentoRequest request);

    MedicamentoResponse buscarPorId(Long id);

    MedicamentoResponse atualiza(Long id, MedicamentoRequest request);

    Page<MedicamentoResponse> buscarTodos(int page, int size);
}
