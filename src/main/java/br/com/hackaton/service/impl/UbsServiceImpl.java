package br.com.hackaton.service.impl;

import br.com.hackaton.controller.request.UbsRequest;
import br.com.hackaton.controller.response.UbsComMedicamentoResponse;
import br.com.hackaton.controller.response.UbsResponse;
import br.com.hackaton.entity.Medicamento;
import br.com.hackaton.entity.Posologia;
import br.com.hackaton.entity.Receita;
import br.com.hackaton.entity.Ubs;
import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.repository.UbsRepository;
import br.com.hackaton.service.EmailService;
import br.com.hackaton.service.ReceitaService;
import br.com.hackaton.service.UbsParserService;
import br.com.hackaton.service.UbsService;
import br.com.hackaton.utils.GeoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.hackaton.exception.CodigoError.ERRO_AO_PROCESSAR_UBS_PROXIMAS_COM_MEDICAMENTO;
import static br.com.hackaton.exception.CodigoError.UBS_NAO_ENCONTRADA;

@Service
@RequiredArgsConstructor
public class UbsServiceImpl implements UbsService {

    public static final double DISTANCIA_MAXIMA = 500.0;
    private final EmailService emailService;
    private final UbsParserService ubsParserService;
    private final ReceitaService receitaService;
    private final UbsRepository repository;

    @Override
    public void cria(UbsRequest request) {

        var ubs = new Ubs(request);

        repository.save(ubs);
    }

    @Override
    public UbsResponse buscarPorId(Long id) {

        var ubs = repository.findById(id).orElseThrow(() -> new ExceptionAdvice(UBS_NAO_ENCONTRADA));

        return new UbsResponse(ubs);
    }

    @Override
    public UbsResponse atualiza(Long id, UbsRequest request) {

        var ubs = repository.findById(id).orElseThrow(() -> new ExceptionAdvice(UBS_NAO_ENCONTRADA));

        ubs.atualiza(request);

        ubs = repository.save(ubs);

        return new UbsResponse(ubs);
    }

    @Override
    public Page<UbsResponse> buscarTodos(int page, int size) {
        
        var pageRequest = PageRequest.of(page, size);

        var ubsPage = repository.findAll(pageRequest);

        return ubsPage.map(UbsResponse::new);
    }

    @Override
    public List<UbsComMedicamentoResponse> encontrarUbsProximasDePacienteComMedicamentos(Long receitaId) {
        var receita = receitaService.buscaEntidadePorId(receitaId);
        return getUbsComMedicamentoResponses(receita);
    }

    @Override
    public void enviarEmailUbsProximasDePacienteComMedicamentos(Long receitaId) {
        try {
            var receita = receitaService.buscaEntidadePorId(receitaId);
            var ubsComMedicamentoResponses = getUbsComMedicamentoResponses(receita);
                emailService.enviarEmail(
                        receita.getPaciente().getEmail(),
                        "UBS PRÓXIMAS COM MEDICAMENTOS DISPONÍVEIS",
                        ubsParserService.parseUbsComMedicamentoHtml(ubsComMedicamentoResponses)
                );
        } catch (IOException e) {
            throw new ExceptionAdvice(ERRO_AO_PROCESSAR_UBS_PROXIMAS_COM_MEDICAMENTO);
        }
    }

    private List<UbsComMedicamentoResponse> getUbsComMedicamentoResponses(Receita receita) {
        var pacienteEndereco = receita.getPaciente().getEndereco();
        var latitude = pacienteEndereco.getLatitude();
        var longitude = pacienteEndereco.getLongitude();
        return receita.getPosologias().stream()
                .flatMap(posologia -> buscarUbsProximasComMedicamento(latitude, longitude, posologia))
                .collect(Collectors.toMap(response ->
                        response.getMedicamento().getId(),
                        response -> response,
                        this::manterUbsMaisProxima
                ))
                .values().stream()
                .sorted(Comparator.comparing(UbsComMedicamentoResponse::getDistancia))
                .toList();
    }

    private Stream<UbsComMedicamentoResponse> buscarUbsProximasComMedicamento(Double latitude, Double longitude, Posologia posologia) {
        return repository.buscarUbsPorDistanciaMaxima(latitude, longitude, DISTANCIA_MAXIMA).stream()
                .filter(ubs -> ubs.temMedicamentoDisponivel(posologia.getMedicamento(), BigInteger.valueOf(posologia.getQuantidade())))
                .map(ubs -> mapearUbsComMedicamentoResponse(ubs, latitude, longitude, posologia.getMedicamento()));
    }

    private UbsComMedicamentoResponse manterUbsMaisProxima(UbsComMedicamentoResponse existing, UbsComMedicamentoResponse replacement) {
        return existing.getDistancia() <= replacement.getDistancia() ? existing : replacement;
    }

    private UbsComMedicamentoResponse mapearUbsComMedicamentoResponse(Ubs ubs, Double latitude, Double longitude, Medicamento medicamento) {
        return new UbsComMedicamentoResponse(ubs, calcularDistancia(ubs, latitude, longitude), medicamento);
    }

    private static Double calcularDistancia(Ubs ubs, Double latitude, Double longitude) {
        return GeoUtils.calcularDistancia(latitude, longitude, ubs.getEndereco().getLatitude(), ubs.getEndereco().getLongitude());
    }
}
