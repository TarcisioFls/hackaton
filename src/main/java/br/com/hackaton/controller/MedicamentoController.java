package br.com.hackaton.controller;

import br.com.hackaton.controller.request.MedicamentoRequest;
import br.com.hackaton.controller.response.MedicamentoResponse;
import br.com.hackaton.service.MedicamentoService;
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
@RequestMapping("/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void criar(@RequestBody MedicamentoRequest request) {
        medicamentoService.cria(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoResponse> lista(@PathVariable Long id) {
        var medicamentoResponse = medicamentoService.buscarPorId(id);

        return ResponseEntity.ok(medicamentoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoResponse> atualiza(@PathVariable Long id, @RequestBody MedicamentoRequest request) {
        var medicamentoResponse = medicamentoService.atualiza(id, request);

        return ResponseEntity.ok(medicamentoResponse);
    }

    @GetMapping
    public ResponseEntity<Page<MedicamentoResponse>> lista(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "50") int size) {
        var medicamentoResponse = medicamentoService.buscarTodos(page, size);

        return ResponseEntity.ok(medicamentoResponse);
    }

}
