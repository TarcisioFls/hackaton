package br.com.hackaton.utilsTest;

import br.com.hackaton.controller.request.PosologiaRequest;
import br.com.hackaton.controller.response.PosologiaResponse;
import br.com.hackaton.entity.Posologia;

public class PosologiaUtils {

    private PosologiaUtils() {}

    public static PosologiaRequest buildPosologiaRequest() {
        return PosologiaRequest.builder()
                .medicamentoId(1L)
                .quantidade(1)
                .descricao("descricao")
                .build();
    }

    public static PosologiaResponse buildPosologiaResponse() {
        return PosologiaResponse.builder()
                .descricao("descricao")
                .quantidade(1)
                .medicamento(MedicamentoUtils.buildMedicamentoResponse())
                .build();
    }

    public static Posologia buildPosologia() {
        return Posologia.builder()
                .id(1L)
                .descricao("descricao")
                .quantidade(1)
                .medicamento(MedicamentoUtils.buildMedicamento())
                .build();

    }
}
