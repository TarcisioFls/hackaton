package br.com.hackaton.service;

import br.com.hackaton.controller.response.UbsComMedicamentoResponse;

import java.io.IOException;
import java.util.List;

public interface UbsParserService {
    String parseUbsComMedicamentoHtml(List<UbsComMedicamentoResponse> ubsComMedicamentoResponseList) throws IOException;
}
