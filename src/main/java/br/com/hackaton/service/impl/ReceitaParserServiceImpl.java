package br.com.hackaton.service.impl;

import br.com.hackaton.entity.Receita;
import br.com.hackaton.service.ReceitaParserService;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
public class ReceitaParserServiceImpl implements ReceitaParserService {

    @Override
    public String parseReceitaHtml(Receita receita) throws IOException {
        var input = new File("src/main/resources/templates/receita.html");
        var doc = Jsoup.parse(input, "UTF-8");

        doc.select("span:contains({{medicoNome}})").first().text(receita.getMedico().getNome());
        doc.select("span:contains({{medicoCrm}})").first().text(receita.getMedico().getCrm());
        doc.select("span:contains({{medicoTelefone}})").first().text(receita.getMedico().getTelefone());
        doc.select("span:contains({{medicamentosQuantidade}})").first().text(String.valueOf(receita.getPosologias().size()));
        doc.select("span:contains({{pacienteNome}})").first().text(receita.getPaciente().getNome());
        doc.select("span:contains({{data}})").first().text(receita.getDataHoraCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        var tbody = doc.select("tbody").first();
        var medicamentosHtml = receita.getPosologias().stream()
                .map(posologia -> "<tr><td>" + posologia.getMedicamento().getNome() + "</td><td>" + posologia.getDescricao() + "</td><td>" + posologia.getQuantidade() + "</td></tr>")
                .collect(Collectors.joining());

        tbody.html(medicamentosHtml);

        return doc.html();
    }

}
