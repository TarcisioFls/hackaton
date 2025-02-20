package br.com.hackaton.service.impl;

import br.com.hackaton.controller.response.UbsComMedicamentoResponse;
import br.com.hackaton.service.UbsParserService;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
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

        var ubsMap = new HashMap<String, UbsComMedicamentoResponse>();
        for (var response : ubsComMedicamentoResponseList) {
            ubsMap.putIfAbsent(response.getNome(), response);
        }

        for (var ubsEntry : ubsMap.values()) {
            var ubsItem = ubsItemTemplate.clone();

            ubsItem.select("span:contains({{ubs.nome}})").first().text(ubsEntry.getNome());
            ubsItem.select("span:contains({{ubs.endereco}})").first().text(
                    ubsEntry.getEndereco().getLogradouro() + ", " +
                            ubsEntry.getEndereco().getNumero() + ", " +
                            ubsEntry.getEndereco().getBairro() + ", " +
                            ubsEntry.getEndereco().getCidade() + " - " +
                            ubsEntry.getEndereco().getEstado()
            );
            ubsItem.select("span:contains({{ubs.telefone}})").first().text(ubsEntry.getTelefone());
            ubsItem.select("span:contains({{ubs.distancia}})").first().text(df.format(ubsEntry.getDistancia()));

            var medicamentoList = ubsItem.selectFirst(".med-list");
            var medicamentoItem = medicamentoList.selectFirst(".med-item").clone();
            medicamentoList.empty();

            for (var response : ubsComMedicamentoResponseList) {
                if (response.getNome().equals(ubsEntry.getNome())) {
                    var medItem = medicamentoItem.clone();
                    medItem.select("span:contains({{medicamento.nome}})").first().text(response.getMedicamento().getNome() + ", ");
                    medItem.select("span:contains({{medicamento.tarja}})").first().text(response.getMedicamento().getTarja().toString() + ", ");
                    medItem.select("span:contains({{medicamento.sku}})").first().text(response.getMedicamento().getSku());
                    medicamentoList.appendChild(medItem);
                }
            }

            ubsListElement.appendChild(ubsItem);
        }

        return doc.outerHtml();
    }
}
