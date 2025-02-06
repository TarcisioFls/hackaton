package br.com.hackaton.service;

import br.com.hackaton.controller.request.ReceitaRequest;
import br.com.hackaton.controller.response.ReceitaResponse;
import org.springframework.data.domain.Page;

public interface ReceitaService {

    void criar(ReceitaRequest request);

    ReceitaResponse buscarPorId(Long id);

    Page<ReceitaResponse> buscarTodos(int page, int size);

    Page<ReceitaResponse> buscarPorMedicoId(Long id, int page, int size);
}
