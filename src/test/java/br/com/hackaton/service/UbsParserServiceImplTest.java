package br.com.hackaton.service;

import br.com.hackaton.controller.response.UbsComMedicamentoResponse;
import br.com.hackaton.service.impl.UbsParserServiceImpl;
import br.com.hackaton.utilsTest.MedicamentoUtils;
import br.com.hackaton.utilsTest.UbsUtils;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class UbsParserServiceImplTest {

    private final UbsParserService service = new UbsParserServiceImpl();

    @Nested
    class ParseUbsComMedicamentoHtml {
        @Test
        void deveParsearUbsComMedicamentoHtml() throws IOException {
            // Given
            var medicamento = MedicamentoUtils.buildMedicamento();
            var ubs = UbsUtils.buildUbs();
            var response = new UbsComMedicamentoResponse(ubs, 10.0, medicamento);

            // When
            var html = service.parseUbsComMedicamentoHtml(List.of(response));

            // Then
            var doc = Jsoup.parse(html);
            assertEquals(ubs.getNome(), doc.select("span:contains(" + ubs.getNome() + ")").first().text());
            assertEquals(ubs.getTelefone(), doc.select("span:contains(" + ubs.getTelefone() + ")").first().text());
            assertEquals(String.valueOf(response.getDistancia().intValue()), doc.select("span:contains(" + response.getDistancia().intValue() + ")").first().text());
        }
    }

}