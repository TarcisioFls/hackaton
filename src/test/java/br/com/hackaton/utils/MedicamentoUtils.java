package br.com.hackaton.utils;

import br.com.hackaton.controller.request.MedicamentoRequest;
import br.com.hackaton.controller.response.MedicamentoResponse;
import br.com.hackaton.entity.Tarja;

public class MedicamentoUtils {

    private MedicamentoUtils() {}


    public static MedicamentoRequest buildMedicamentoRequest() {
        return MedicamentoRequest.builder()
                .nome("Dipirona")
                .tarja(Tarja.AMARELA)
                .build();
    }

    public static MedicamentoResponse buildMedicamentoResponse() {
        return MedicamentoResponse.builder()
                .id(1L)
                .nome("Dipirona")
                .tarja(Tarja.AMARELA)
                .sku("123456")
                .build();
    }
}
