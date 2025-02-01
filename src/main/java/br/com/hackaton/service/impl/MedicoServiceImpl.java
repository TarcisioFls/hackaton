package br.com.hackaton.service.impl;

import br.com.hackaton.controller.request.MedicoRequest;
import br.com.hackaton.controller.response.MedicoResponse;
import br.com.hackaton.entity.Medico;
import br.com.hackaton.exception.CodigoError;
import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.repository.MedicoRepository;
import br.com.hackaton.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static br.com.hackaton.exception.CodigoError.MEDICO_NAO_ENCONTRADO;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public MedicoResponse cria(MedicoRequest request) {
        var medico = new Medico(request);
        medicoRepository.save(medico);

        return new MedicoResponse(medico);

    }

    @Override
    public MedicoResponse buscarPorId(Long id) {
        var medico = medicoRepository.findById(id).orElseThrow(() -> new ExceptionAdvice(MEDICO_NAO_ENCONTRADO));

        return new MedicoResponse(medico);
    }

    @Override
    public MedicoResponse atualiza(Long id, MedicoRequest request) {
        var medicoParaAtualizar = medicoRepository.findById(id).orElseThrow(() -> new ExceptionAdvice(MEDICO_NAO_ENCONTRADO));

        if(!medicoParaAtualizar.getNome().equals(request.nome())) {
            medicoParaAtualizar.setNome(request.nome());
        }
        if(!medicoParaAtualizar.getCrm().equals(request.crm())) {
            medicoParaAtualizar.setCrm(request.crm());
        }
        if(!medicoParaAtualizar.getEspecialidade().equals(request.especialidade())){
            medicoParaAtualizar.setEspecialidade(request.especialidade());
        }
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
