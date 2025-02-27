package br.com.hackaton.service.impl;

import br.com.hackaton.controller.request.MedicoRequest;
import br.com.hackaton.controller.response.MedicoResponse;
import br.com.hackaton.entity.Medico;
import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.repository.MedicoRepository;
import br.com.hackaton.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static br.com.hackaton.exception.CodigoError.MEDICO_NAO_ENCONTRADO;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository medicoRepository;

    @Override
    public void criar(MedicoRequest request) {
        var medico = new Medico(request);
        medicoRepository.save(medico);
    }

    @Override
    public MedicoResponse buscarPorId(Long id) {
        var medico = medicoRepository.findById(id).orElseThrow(() -> new ExceptionAdvice(MEDICO_NAO_ENCONTRADO));

        return new MedicoResponse(medico);
    }

    @Override
    public MedicoResponse atualiza(Long id, MedicoRequest request) {
        var medicoParaAtualizar = medicoRepository.findById(id).orElseThrow(() -> new ExceptionAdvice(MEDICO_NAO_ENCONTRADO));

        medicoParaAtualizar.atualizar(request);

        medicoRepository.save(medicoParaAtualizar);

        return new MedicoResponse(medicoParaAtualizar);
    }

    @Override
    public Page<MedicoResponse> buscarTodos(int page, int size) {
        var medicos = PageRequest.of(page, size);
        return medicoRepository.findAll(medicos).map(MedicoResponse::new);
    }

    @Override
    public void excluir(Long id) {
        var medico = medicoRepository.findById(id).orElseThrow(() -> new ExceptionAdvice(MEDICO_NAO_ENCONTRADO));
        medicoRepository.delete(medico);
    }
}
