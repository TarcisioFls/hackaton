package br.com.hackaton.service.impl;

import br.com.hackaton.controller.request.PacienteRequest;
import br.com.hackaton.controller.response.PacienteResponse;
import br.com.hackaton.entity.Paciente;
import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.repository.PacienteRepository;
import br.com.hackaton.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static br.com.hackaton.exception.CodigoError.PACIENTE_NAO_ENCONTRADO;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    @Override
    public void criar(PacienteRequest request) {

        var paciente = new Paciente(request);

        pacienteRepository.save(paciente);
    }

    @Override
    public Page<PacienteResponse> buscarTodos(int page, int size) {

        var pageRequest = PageRequest.of(page, size);

        var pacientePage = pacienteRepository.findAll(pageRequest);

        return pacientePage.map(PacienteResponse::new);
    }

    @Override
    public PacienteResponse buscarPorId(Long id) {

        var paciente = pacienteRepository.findById(id).orElseThrow(() -> new ExceptionAdvice(PACIENTE_NAO_ENCONTRADO));

        return new PacienteResponse(paciente);
    }

    @Override
    public PacienteResponse atualizar(Long id, PacienteRequest request) {

        var paciente = pacienteRepository.findById(id).orElseThrow(() -> new ExceptionAdvice(PACIENTE_NAO_ENCONTRADO));

        paciente.atualiza(request);

        pacienteRepository.save(paciente);

        return new PacienteResponse(paciente);
    }
}
