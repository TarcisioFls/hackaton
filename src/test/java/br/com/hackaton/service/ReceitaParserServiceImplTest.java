package br.com.hackaton.service;

import br.com.hackaton.service.impl.ReceitaParserServiceImpl;
import br.com.hackaton.utilsTest.MedicoUtils;
import br.com.hackaton.utilsTest.PacienteUtils;
import br.com.hackaton.utilsTest.PosologiaUtils;
import br.com.hackaton.utilsTest.ReceitaUtils;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class ReceitaParserServiceImplTest {

    private final ReceitaParserService service = new ReceitaParserServiceImpl();

    @Nested
    class ParseReceitaHtml {
        @Test
        void deveParsearReceitaHtml() throws IOException {
            // Given
            var medico = MedicoUtils.buildMedico();
            var paciente = PacienteUtils.buildPaciente();
            var posologia = PosologiaUtils.buildPosologia();
            var receita = ReceitaUtils.buildReceita();

            // When
            var html = service.parseReceitaHtml(receita);

            // Then
            var doc = Jsoup.parse(html);
            assertEquals(medico.getNome(), doc.select("span:contains(" + medico.getNome() + ")").first().text());
            assertEquals(medico.getCrm(), doc.select("span:contains(" + medico.getCrm() + ")").first().text());
            assertEquals(medico.getTelefone(), doc.select("span:contains(" + medico.getTelefone() + ")").first().text());
            assertEquals(paciente.getNome(), doc.select("span:contains(" + paciente.getNome() + ")").first().text());
            assertEquals(posologia.getMedicamento().getNome(), doc.select("td:contains(" + posologia.getMedicamento().getNome() + ")").first().text());
            assertEquals(posologia.getDescricao(), doc.select("td:contains(" + posologia.getDescricao() + ")").first().text());
            assertEquals(posologia.getQuantidade().toString(), doc.select("td:contains(" + posologia.getQuantidade() + ")").first().text());
        }
    }

}