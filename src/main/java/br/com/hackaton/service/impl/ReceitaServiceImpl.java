package br.com.hackaton.service.impl;

import br.com.hackaton.controller.request.ReceitaRequest;
import br.com.hackaton.controller.response.ReceitaResponse;
import br.com.hackaton.entity.Posologia;
import br.com.hackaton.entity.Receita;
import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.repository.ReceitaRepository;
import br.com.hackaton.service.EmailService;
import br.com.hackaton.service.MedicamentoService;
import br.com.hackaton.service.MedicoService;
import br.com.hackaton.service.PacienteService;
import br.com.hackaton.service.ReceitaParserService;
import br.com.hackaton.service.ReceitaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static br.com.hackaton.exception.CodigoError.ERRO_AO_PROCESSAR_RECEITA_HTML;
import static br.com.hackaton.exception.CodigoError.RECEITA_NAO_ENCONTRADA;

@Service
public class ReceitaServiceImpl implements ReceitaService {

    private final ReceitaRepository receitaRepository;

    private final ReceitaParserService receitaParserService;

    private final EmailService emailService;

    private final MedicoService medicoService;

    private final PacienteService pacienteService;

    private final MedicamentoService medicamentoService;

    public ReceitaServiceImpl(ReceitaRepository receitaRepository, ReceitaParserService receitaParserService, EmailService emailService, MedicoService medicoService, PacienteService pacienteService, MedicamentoService medicamentoService) {
        this.receitaRepository = receitaRepository;
        this.receitaParserService = receitaParserService;
        this.emailService = emailService;
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

    @Override
    public Receita buscaEntidadePorId(Long id) {
        return receitaRepository.findById(id)
                .orElseThrow(() -> new ExceptionAdvice(RECEITA_NAO_ENCONTRADA));
    }

    @Override
    public ReceitaResponse buscarPorId(Long id) {

        var receita = buscaReceitaPorId(id);

        return new ReceitaResponse(receita);
    }

    @Override
    public Page<ReceitaResponse> buscarTodos(int page, int size) {

        var pageRequest = PageRequest.of(page, size);

        return receitaRepository.findAll(pageRequest).map(ReceitaResponse::new);
    }

    @Override
    public Page<ReceitaResponse> buscarPorMedicoId(Long id, int page, int size) {

        var pageRequest = PageRequest.of(page, size);

        return receitaRepository.findByMedicoId(id, pageRequest).map(ReceitaResponse::new);
    }

    @Override
    public Page<ReceitaResponse> buscarPorPacienteId(Long id, int page, int size) {

        var pageRequest = PageRequest.of(page, size);

        return receitaRepository.findByPacienteId(id, pageRequest).map(ReceitaResponse::new);
    }

    @Override
    public void deletar(Long id) {

        var receita = buscaReceitaPorId(id);

        receitaRepository.delete(receita);
    }

    @Override
    public void enviarEmail(Long id) {
        try {
            var receita = buscaReceitaPorId(id);
            emailService.enviarEmail(
                    receita.getPaciente().getEmail(),
                    "RECEITA DIGITAL UBS",
                    receitaParserService.parseReceitaHtml(receita)
            );
        } catch (IOException e) {
            throw new ExceptionAdvice(ERRO_AO_PROCESSAR_RECEITA_HTML);
        }
    }

    private Receita buscaReceitaPorId(Long id) {
        return receitaRepository.findById(id).orElseThrow(() -> new ExceptionAdvice(RECEITA_NAO_ENCONTRADA));
    }
}
