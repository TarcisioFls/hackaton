package br.com.hackaton.service.impl;

import br.com.hackaton.controller.request.ReceitaRequest;
import br.com.hackaton.entity.Posologia;
import br.com.hackaton.entity.Receita;
import br.com.hackaton.repository.ReceitaRepository;
import br.com.hackaton.service.MedicamentoService;
import br.com.hackaton.service.MedicoService;
import br.com.hackaton.service.PacienteService;
import br.com.hackaton.service.ReceitaService;
import org.springframework.stereotype.Service;

@Service
public class ReceitaServiceImpl implements ReceitaService {

    private final ReceitaRepository receitaRepository;

    private final MedicoService medicoService;

    private final PacienteService pacienteService;

    private final MedicamentoService medicamentoService;

    public ReceitaServiceImpl(ReceitaRepository receitaRepository, MedicoService medicoService, PacienteService pacienteService, MedicamentoService medicamentoService) {
        this.receitaRepository = receitaRepository;
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
        this.medicamentoService = medicamentoService;
    }

    @Override
    public void criar(ReceitaRequest request) {

        var medicoResponse = medicoService.buscarPorId(request.medicoId());
        var pacienteResponse = pacienteService.buscarPorId(request.pacienteId());

        var receita = new Receita(medicoResponse, pacienteResponse);

        var posologias = request.posologias().stream().map(posologiaRequest -> {
            var medicamentoResponse = medicamentoService.buscarPorId(posologiaRequest.medicamentoId());
            return new Posologia(posologiaRequest.descricao(), posologiaRequest.quantidade(), medicamentoResponse, receita);
        }).toList();

        receita.setPosologias(posologias);
        receitaRepository.save(receita);
    }
}
