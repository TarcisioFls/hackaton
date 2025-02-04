package br.com.hackaton.service.impl;

import br.com.hackaton.controller.request.PacienteRequest;
import br.com.hackaton.controller.response.PacienteResponse;
import br.com.hackaton.entity.Paciente;
import br.com.hackaton.repository.PacienteRepository;
import br.com.hackaton.service.PacienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

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
}
