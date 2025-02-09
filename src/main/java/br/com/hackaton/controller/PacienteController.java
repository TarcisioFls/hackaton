package br.com.hackaton.controller;

import br.com.hackaton.controller.request.PacienteRequest;
import br.com.hackaton.controller.response.PacienteResponse;
import br.com.hackaton.service.PacienteService;
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
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void criar (@RequestBody PacienteRequest request) {

        pacienteService.criar(request);

    }

    @GetMapping
    public ResponseEntity<Page<PacienteResponse>> buscarTodos(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "50") int size) {

        var pacienteResponsePage = pacienteService.buscarTodos(page, size);

        return ResponseEntity.ok(pacienteResponsePage);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarPorId(@PathVariable Long id) {

        var pacienteResponse = pacienteService.buscarPorId(id);

        return ResponseEntity.ok(pacienteResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> atualizar(@PathVariable Long id, @RequestBody PacienteRequest request) {

        var pacienteResponse = pacienteService.atualizar(id, request);

        return ResponseEntity.ok(pacienteResponse);
    }
}
