package br.com.hackaton.entity;

import br.com.hackaton.controller.response.MedicamentoResponse;
import br.com.hackaton.controller.response.UbsResponse;
import br.com.hackaton.exception.ExceptionAdvice;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.math.BigInteger;

import static br.com.hackaton.exception.CodigoError.QUANTIDADE_ADICIONADA_ESTOQUE_NEGATIVA;
import static br.com.hackaton.exception.CodigoError.QUANTIDADE_ESTOQUE_NEGATIVA;

@Getter
@Entity
@Table(name = "estoque")
public class Estoque extends BaseEntity {

    private BigInteger quantidade;

    @ManyToOne
    private Medicamento medicamento;

    @ManyToOne
    private Ubs ubs;

    public Estoque() {}

    public Estoque(BigInteger quantidade, Medicamento medicamento, Ubs ubs) {

        this.quantidade = quantidade;
        this.medicamento = medicamento;
        this.ubs = ubs;
    }

    public Estoque(BigInteger quantidade, MedicamentoResponse medicamentoResponse, UbsResponse ubsResponse) {

        this.quantidade = quantidade;
        this.medicamento = new Medicamento(medicamentoResponse);
        this.ubs = new Ubs(ubsResponse);
    }

    public void adiciona(BigInteger quantidade) {

        if (quantidade.compareTo(BigInteger.ZERO) < 0) {
            throw new ExceptionAdvice(QUANTIDADE_ADICIONADA_ESTOQUE_NEGATIVA);
        }

        this.quantidade = this.quantidade.add(quantidade);
    }

    public void retira(BigInteger quantidade) {

        if (quantidade.compareTo(BigInteger.ZERO) < 0) {
            throw new ExceptionAdvice(QUANTIDADE_ADICIONADA_ESTOQUE_NEGATIVA);
        }

        if (this.quantidade.compareTo(quantidade) < 0) {
            throw new ExceptionAdvice(QUANTIDADE_ESTOQUE_NEGATIVA);
        }

        this.quantidade = this.quantidade.subtract(quantidade);
    }
}
