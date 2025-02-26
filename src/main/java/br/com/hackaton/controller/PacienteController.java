package br.com.hackaton.controller;

import br.com.hackaton.controller.request.PacienteRequest;
import br.com.hackaton.controller.response.PacienteResponse;
import br.com.hackaton.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void criar(@Valid @RequestBody PacienteRequest request) {
        pacienteService.criar(request);
    }

    @GetMapping
    public ResponseEntity<Page<PacienteResponse>> buscarTodos(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "50") int size) {

        return ResponseEntity.ok(pacienteService.buscarTodos(page, size));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> atualizar(@PathVariable Long id, @Valid @RequestBody PacienteRequest request) {

        return ResponseEntity.ok(pacienteService.atualizar(id, request));
    }
}
