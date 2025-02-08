package br.com.hackaton.controller.response;

import br.com.hackaton.entity.Medicamento;
import br.com.hackaton.entity.Ubs;
import lombok.Getter;

@Getter
public class UbsComMedicamentoResponse extends UbsResponse {

    private final Double distancia;

    private final MedicamentoResponse medicamento;

    public UbsComMedicamentoResponse(Ubs ubs, Double distancia, Medicamento medicamento) {
        super(ubs);
        this.distancia = distancia;
        this.medicamento = new MedicamentoResponse(medicamento);
    }

}
