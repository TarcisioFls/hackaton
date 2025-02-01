package br.com.hackaton.service;

import br.com.hackaton.controller.request.MedicoRequest;
import br.com.hackaton.controller.response.MedicoResponse;
import org.springframework.data.domain.Page;

public interface MedicoService {

    MedicoResponse cria(MedicoRequest request);

    MedicoResponse buscarPorId(Long id);

    MedicoResponse atualiza(Long id, MedicoRequest request);

    Page<MedicoResponse> buscarTodos(int page, int size);

    void excluir(Long id);


}
