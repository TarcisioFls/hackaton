package br.com.hackaton.service;

import br.com.hackaton.controller.request.PacienteRequest;
import br.com.hackaton.controller.response.PacienteResponse;
import org.springframework.data.domain.Page;

public interface PacienteService {


    void criar(PacienteRequest request);

    Page<PacienteResponse> buscarTodos(int page, int size);

    PacienteResponse buscarPorId(Long id);

    PacienteResponse atualizar(Long id, PacienteRequest request);
}
