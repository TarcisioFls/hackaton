package br.com.hackaton.controller;

import br.com.hackaton.controller.request.UbsRequest;
import br.com.hackaton.controller.response.UbsResponse;
import br.com.hackaton.service.UbsService;
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
@RequestMapping("/ubs")
public class UbsController {

    private final UbsService ubsService;

    public UbsController(UbsService ubsService) {
        this.ubsService = ubsService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void criar(@RequestBody UbsRequest request) {

        ubsService.cria(request);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UbsResponse> buscarPorId(@PathVariable Long id) {

        var ubsResponse = ubsService.buscarPorId(id);

        return ResponseEntity.ok(ubsResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UbsResponse> atualiza(@PathVariable Long id, @RequestBody UbsRequest request) {

        var ubsResponse = ubsService.atualiza(id, request);

        return ResponseEntity.ok(ubsResponse);
    }

    @GetMapping
    public ResponseEntity<Page<UbsResponse>> buscarTodos(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "50") int size) {

        var ubsResponse = ubsService.buscarTodos(page, size);

        return ResponseEntity.ok(ubsResponse);
    }

}
