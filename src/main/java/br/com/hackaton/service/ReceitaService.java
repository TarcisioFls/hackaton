package br.com.hackaton.service;

import br.com.hackaton.controller.request.ReceitaRequest;
import br.com.hackaton.controller.response.ReceitaResponse;
import br.com.hackaton.entity.Receita;
import org.springframework.data.domain.Page;

public interface ReceitaService {

    void criar(ReceitaRequest request);

    Receita buscaEntidadePorId(Long id);

    ReceitaResponse buscarPorId(Long id);

    Page<ReceitaResponse> buscarTodos(int page, int size);

    Page<ReceitaResponse> buscarPorMedicoId(Long id, int page, int size);

    Page<ReceitaResponse> buscarPorPacienteId(Long id, int page, int size);

    void deletar(Long id);

    void enviarEmail(Long id);
}
