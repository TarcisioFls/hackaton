package br.com.hackaton.entity;

import br.com.hackaton.controller.response.MedicamentoResponse;
import br.com.hackaton.exception.ExceptionAdvice;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.With;
import lombok.experimental.SuperBuilder;

import static br.com.hackaton.exception.CodigoError.DESCRICAO_POSOLOGIA_OBRIGATORIA;
import static br.com.hackaton.exception.CodigoError.QUANTIDADE_POSOLOGIA_MAIOR_QUE_ZERO;
import static br.com.hackaton.exception.CodigoError.QUANTIDADE_POSOLOGIA_OBRIGATORIA;

@Getter
@Entity
@SuperBuilder
@Table(name = "posologia")
public class Posologia extends BaseEntity {

    @NotBlank
    private String descricao;

    @NotNull
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamento;

    @ManyToOne
    private Receita receita;

    public Posologia() {}

    public Posologia(String descricao, Integer quantidade, MedicamentoResponse medicamentoResponse, Receita receita) {

        this.descricao = validaDescricao(descricao);
        this.quantidade = validaQuantidade(quantidade);
        this.medicamento = new Medicamento(medicamentoResponse);
        this.receita = receita;
    }

    private @NotNull Integer validaQuantidade(Integer quantidade) {

        if (quantidade == null) {
            throw new ExceptionAdvice(QUANTIDADE_POSOLOGIA_OBRIGATORIA);
        }

        if (quantidade <= 0) {
            throw new ExceptionAdvice(QUANTIDADE_POSOLOGIA_MAIOR_QUE_ZERO);
        }
        return quantidade;
    }

    private String validaDescricao(String descricao) {

        if (descricao == null || descricao.isBlank()) {
            throw new ExceptionAdvice(DESCRICAO_POSOLOGIA_OBRIGATORIA);
        }
        return descricao;
    }
}
