package br.com.hackaton.service;

import br.com.hackaton.controller.request.PacienteRequest;
import br.com.hackaton.entity.Paciente;
import br.com.hackaton.repository.PacienteRepository;
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
}
