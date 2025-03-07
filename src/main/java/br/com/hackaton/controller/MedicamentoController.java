package br.com.hackaton.controller;

import br.com.hackaton.controller.request.MedicamentoRequest;
import br.com.hackaton.controller.response.MedicamentoResponse;
import br.com.hackaton.service.MedicamentoService;
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
@RequestMapping("/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void criar(@Valid @RequestBody MedicamentoRequest request) {
        medicamentoService.cria(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoResponse> buscaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicamentoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoResponse> atualiza(@PathVariable Long id, @Valid @RequestBody MedicamentoRequest request) {
        return ResponseEntity.ok(medicamentoService.atualiza(id, request));
    }

    @GetMapping
    public ResponseEntity<Page<MedicamentoResponse>> listaPaginado(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "50") int size) {
        return ResponseEntity.ok(medicamentoService.buscarTodos(page, size));
    }

}
