package br.com.hackaton.controller;

import br.com.hackaton.controller.request.ReceitaRequest;
import br.com.hackaton.controller.response.ReceitaResponse;
import br.com.hackaton.service.ReceitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/receitas")
public class ReceitaController {

    private final ReceitaService receitaService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void criar(@Valid @RequestBody ReceitaRequest request) {
        receitaService.criar(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(receitaService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<ReceitaResponse>> buscarTodos(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "50") int size) {
        return ResponseEntity.ok(receitaService.buscarTodos(page, size));
    }

    @GetMapping("/medico/{id}")
    public ResponseEntity<Page<ReceitaResponse>> buscarPorMedico(@PathVariable Long id,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "50") int size) {
        return ResponseEntity.ok(receitaService.buscarPorMedicoId(id, page, size));
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<Page<ReceitaResponse>> buscarPorPaciente(@PathVariable Long id,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "50") int size) {
        return ResponseEntity.ok(receitaService.buscarPorPacienteId(id, page, size));
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        receitaService.deletar(id);
    }

    @ResponseStatus(OK)
    @PostMapping("/{id}/enviar-email")
    public void enviar(@PathVariable Long id) {
        receitaService.enviarEmail(id);
    }

}
