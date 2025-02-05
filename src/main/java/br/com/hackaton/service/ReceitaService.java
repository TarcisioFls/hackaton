package br.com.hackaton.service;

import br.com.hackaton.controller.request.ReceitaRequest;
import br.com.hackaton.controller.response.ReceitaResponse;

public interface ReceitaService {

    void criar(ReceitaRequest request);

    ReceitaResponse buscarPorId(Long id);
}
