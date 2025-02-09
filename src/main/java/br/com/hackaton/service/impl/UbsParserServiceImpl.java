package br.com.hackaton.service.impl;

import br.com.hackaton.controller.response.UbsComMedicamentoResponse;
import br.com.hackaton.service.UbsParserService;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

@Service
public class UbsParserServiceImpl implements UbsParserService {

    @Override
    public String parseUbsComMedicamentoHtml(List<UbsComMedicamentoResponse> ubsComMedicamentoResponseList) throws IOException {
        var df = new DecimalFormat("#.##");
        var doc = Jsoup.parse(getClass().getResourceAsStream("/templates/ubs-proximas.html"), "UTF-8", "");

        var ubsListElement = doc.selectFirst(".ubs-list");
        var ubsItemTemplate = ubsListElement.selectFirst(".ubs-item").clone();
        ubsListElement.empty();

        for (var response : ubsComMedicamentoResponseList) {
            var ubsItem = ubsItemTemplate.clone();

            ubsItem.select("span:contains({{ubs.nome}})").first().text(response.getNome());
            ubsItem.select("span:contains({{ubs.endereco}})").first().text(
                    response.getEndereco().getLogradouro() + ", " +
                            response.getEndereco().getNumero() + ", " +
                            response.getEndereco().getBairro() + ", " +
                            response.getEndereco().getCidade() + " - " +
                            response.getEndereco().getEstado()
            );
            ubsItem.select("span:contains({{ubs.telefone}})").first().text(response.getTelefone());
            ubsItem.select("span:contains({{ubs.distancia}})").first().text(df.format(response.getDistancia()));

            var medicamentoTableBody = ubsItem.selectFirst("tbody");
            var medicamentoRowTemplate = medicamentoTableBody.selectFirst("tr").clone();
            medicamentoTableBody.empty();

            var medicamento = response.getMedicamento();
            var row = medicamentoRowTemplate.clone();
            row.select("span:contains({{medicamento.nome}})").first().text(medicamento.getNome());
            row.select("span:contains({{medicamento.tarja}})").first().text(medicamento.getTarja().toString());
            row.select("span:contains({{medicamento.sku}})").first().text(medicamento.getSku());

            medicamentoTableBody.appendChild(row);
            ubsListElement.appendChild(ubsItem);
        }

        return doc.outerHtml();
    }
}
