package br.com.hackaton.entity;

import br.com.hackaton.controller.request.MedicamentoRequest;
import br.com.hackaton.exception.ExceptionAdvice;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDateTime;

import static br.com.hackaton.exception.CodigoError.NOME_MEDICAMENTO_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.QUANTIDADE_MEDICAMENTO_INVALIDO;
import static br.com.hackaton.exception.CodigoError.QUANTIDADE_MEDICAMENTO_OBRIGATORIO;
import static jakarta.persistence.EnumType.STRING;
import static java.util.Objects.isNull;

@Getter
@Entity
@Table(name = "medicamento")
public class Medicamento extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigInteger quantidade;

    @Column(nullable = false)
    @Enumerated(STRING)
    private Tarja tarja;

    @Column(nullable = false)
    private String sku;

    public Medicamento() {}

    public Medicamento(String nome, BigInteger quantidade, Tarja tarja) {
        this.nome = validaNome(nome);
        this.quantidade = validaQuantidade(quantidade);
        this.tarja = tarja;
        this.sku = gerarSku(nome);
    }

    public Medicamento(MedicamentoRequest request) {
        this(request.nome(), request.quantidade(), request.tarja());
    }

    public void atualiza(MedicamentoRequest request) {
        this.nome = validaNome(request.nome());
        this.quantidade = validaQuantidade(request.quantidade());
        this.tarja = request.tarja();
    }

    private String validaNome(String nome) {
        if (isNull(nome) || nome.isBlank()) {
            throw new ExceptionAdvice(NOME_MEDICAMENTO_OBRIGATORIO);
        }

        return nome;
    }

    private BigInteger validaQuantidade(BigInteger quantidade) {

        if (isNull(quantidade)) {
            throw new ExceptionAdvice(QUANTIDADE_MEDICAMENTO_OBRIGATORIO);
        }

        if (quantidade.intValue() <= 0) {
            throw new ExceptionAdvice(QUANTIDADE_MEDICAMENTO_INVALIDO);
        }

        return quantidade;
    }

    private String gerarSku(String nome) {

        return nome.substring(0, 3).toUpperCase() + "-" + LocalDateTime.now();
    }
}
