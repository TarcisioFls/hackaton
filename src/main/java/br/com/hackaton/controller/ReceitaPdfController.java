package br.com.hackaton.controller;

import br.com.hackaton.service.ReceitaPdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.nonNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/receita/pdf")
public class ReceitaPdfController {

    private static final HttpHeaders HEADERS = new HttpHeaders();
    private static final String ATTACHMENT = "attachment; filename=receita.pdf";

    static {
        HEADERS.add(HttpHeaders.CONTENT_DISPOSITION, ATTACHMENT);
    }

    private final ReceitaPdfService receitaPdfService;

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {
        return receitaPdfService.downloadPdf(id)
                .map(bytes -> ResponseEntity.ok().headers(HEADERS).body(bytes))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
