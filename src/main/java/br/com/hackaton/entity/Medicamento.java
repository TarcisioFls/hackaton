package br.com.hackaton.entity;

import br.com.hackaton.controller.request.MedicamentoRequest;
import br.com.hackaton.controller.response.MedicamentoResponse;
import br.com.hackaton.exception.ExceptionAdvice;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.hackaton.exception.CodigoError.NOME_MEDICAMENTO_OBRIGATORIO;
import static jakarta.persistence.EnumType.STRING;
import static java.util.Objects.isNull;

@Getter
@Entity
@Table(name = "medicamento")
public class Medicamento extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(STRING)
    private Tarja tarja;

    @Column(nullable = false)
    private String sku;

    @OneToMany(mappedBy = "medicamento")
    private List<Estoque> estoque;

    public Medicamento() {}

    public Medicamento(Long id, String nome, Tarja tarja, String sku) {
        super(id);
        this.nome = validaNome(nome);
        this.tarja = tarja;
        this.sku = sku;
    }

    public Medicamento(String nome, Tarja tarja) {

        this.nome = validaNome(nome);
        this.tarja = tarja;
        this.sku = gerarSku(nome);
    }

    public Medicamento(MedicamentoRequest request) {

        this(request.nome(), request.tarja());
    }

    public Medicamento(MedicamentoResponse response) {

        this(response.getId(), response.getNome(), response.getTarja(), response.getSku());
    }

    public void atualiza(MedicamentoRequest request) {

        this.nome = validaNome(request.nome());
        this.tarja = request.tarja();
    }

    private String validaNome(String nome) {

        if (isNull(nome) || nome.isBlank()) {
            throw new ExceptionAdvice(NOME_MEDICAMENTO_OBRIGATORIO);
        }

        return nome;
    }

    private String gerarSku(String nome) {

        return nome.substring(0, 3).toUpperCase() + "-" + LocalDateTime.now();
    }
}
