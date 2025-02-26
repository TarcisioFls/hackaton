package br.com.hackaton.controller;

import br.com.hackaton.controller.request.UbsRequest;
import br.com.hackaton.controller.response.UbsComMedicamentoResponse;
import br.com.hackaton.controller.response.UbsResponse;
import br.com.hackaton.service.UbsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/ubs")
public class UbsController {

    private final UbsService ubsService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void criar(@Valid @RequestBody UbsRequest request) {
        ubsService.cria(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UbsResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ubsService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UbsResponse> atualiza(@PathVariable Long id, @Valid @RequestBody UbsRequest request) {
        return ResponseEntity.ok(ubsService.atualiza(id, request));
    }

    @GetMapping
    public ResponseEntity<Page<UbsResponse>> buscarTodos(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "50") int size) {
        return ResponseEntity.ok(ubsService.buscarTodos(page, size));
    }

    @GetMapping("/receita/{receitaId}")
    public ResponseEntity<List<UbsComMedicamentoResponse>> encontrarUbsProximasDePacienteComMedicamentos(@PathVariable Long receitaId) {
        return ResponseEntity.ok(ubsService.encontrarUbsProximasDePacienteComMedicamentos(receitaId));
    }

    @PostMapping("/receita/{receitaId}/enviar-email")
    public void enviarEmailUbsProximasDePacienteComMedicamentos(@PathVariable Long receitaId) {
        ubsService.enviarEmailUbsProximasDePacienteComMedicamentos(receitaId);
    }

}
