package br.com.hackaton.controller;

import br.com.hackaton.service.ReceitaPdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receita/pdf")
public class ReceitaPdfController {

    private final ReceitaPdfService receitaPdfService;

    public ReceitaPdfController(ReceitaPdfService receitaPdfService) {
        this.receitaPdfService = receitaPdfService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {

        byte[] bytes = receitaPdfService.downloadPdf(id);

        if (bytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=receita.pdf");

            return ResponseEntity.ok().headers(headers).body(bytes);
        }

        return ResponseEntity.notFound().build();
    }

}
