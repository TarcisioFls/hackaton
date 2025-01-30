package br.com.hackaton.service.impl;

import br.com.hackaton.controller.request.MedicamentoRequest;
import br.com.hackaton.controller.response.MedicamentoResponse;
import br.com.hackaton.entity.Medicamento;
import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.repository.MedicamentoRepository;
import br.com.hackaton.service.MedicamentoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static br.com.hackaton.exception.CodigoError.MEDICAMENTO_JA_CADASTRADO;
import static br.com.hackaton.exception.CodigoError.MEDICAMENTO_NAO_ENCONTRADO;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoServiceImpl(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    @Override
    public void cria(MedicamentoRequest request) {

        medicamentoRepository.findByNomeAndTarja(request.nome(), request.tarja()).ifPresent(medicamento -> {
            throw new ExceptionAdvice(MEDICAMENTO_JA_CADASTRADO);
        });

        var medicamento = new Medicamento(request);

        medicamentoRepository.save(medicamento);

    }

    @Override
    public MedicamentoResponse buscarPorId(Long id) {

        var medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new ExceptionAdvice(MEDICAMENTO_NAO_ENCONTRADO));

        return new MedicamentoResponse(medicamento);
    }

    @Override
    public MedicamentoResponse atualiza(Long id, MedicamentoRequest request) {

        var medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new ExceptionAdvice(MEDICAMENTO_NAO_ENCONTRADO));

        medicamento.atualiza(request);

        medicamentoRepository.save(medicamento);

        return new MedicamentoResponse(medicamento);
    }

    @Override
    public Page<MedicamentoResponse> buscarTodos(int page, int size) {

        var pageable = PageRequest.of(page, size);

        return medicamentoRepository.findAll(pageable).map(MedicamentoResponse::new);
    }
}
