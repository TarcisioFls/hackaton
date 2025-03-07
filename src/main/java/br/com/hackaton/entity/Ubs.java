package br.com.hackaton.entity;

import br.com.hackaton.controller.request.UbsRequest;
import br.com.hackaton.controller.response.UbsResponse;
import br.com.hackaton.exception.ExceptionAdvice;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigInteger;
import java.util.List;

import static br.com.hackaton.exception.CodigoError.FIM_ATENDIMENTO_UBS_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.INICIO_ATENDIMENTO_UBS_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.NOME_UBS_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.TELEFONE_UBS_OBRIGATORIO;
import static java.util.Objects.isNull;

@Getter
@Entity
@SuperBuilder
@Table(name = "ubs")
public class Ubs extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String inicioAtendimento;

    @Column(nullable = false)
    private String fimAtendimento;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    @OneToMany(mappedBy = "ubs", fetch = FetchType.EAGER)
    private List<Estoque> estoque;

    public Ubs() {}

    public Ubs(Long id, String nome, String telefone, String inicioAtendimento, String fimAtendimento, Endereco endereco) {

        super(id);
        this.nome = validaNome(nome);
        this.telefone = validaTelefone(telefone);
        this.inicioAtendimento = validaInicioAtendimento(inicioAtendimento);
        this.fimAtendimento = validaFimAtendimento(fimAtendimento);
        this.endereco = endereco;

    }

    public Ubs(String nome, String telefone, String inicioAtendimento, String fimAtendimento, Endereco endereco) {

        this.nome = validaNome(nome);
        this.telefone = validaTelefone(telefone);
        this.inicioAtendimento = validaInicioAtendimento(inicioAtendimento);
        this.fimAtendimento = validaFimAtendimento(fimAtendimento);
        this.endereco = endereco;
    }

    public Ubs(UbsRequest request) {

        this(request.nome(), request.telefone(), request.inicioAtendimento(), request.fimAtendimento(),
                new Endereco(request.endereco()));
    }

    public Ubs(UbsResponse ubsResponse) {

        this(ubsResponse.getId(), ubsResponse.getNome(), ubsResponse.getTelefone(), ubsResponse.getInicioAtendimento(),
                ubsResponse.getFimAtendimento(), new Endereco(ubsResponse.getEndereco()));
    }

    private String validaFimAtendimento(String fimAtendimento) {

        if (isNull(fimAtendimento) || fimAtendimento.isBlank()) {
            throw new ExceptionAdvice(FIM_ATENDIMENTO_UBS_OBRIGATORIO);
        }

        return fimAtendimento;
    }

    private String validaInicioAtendimento(String inicioAtendimento) {

        if (isNull(inicioAtendimento) || inicioAtendimento.isBlank()) {
            throw new ExceptionAdvice(INICIO_ATENDIMENTO_UBS_OBRIGATORIO);
        }

        return inicioAtendimento;
    }

    private String validaTelefone(String telefone) {

        if (isNull(telefone) || telefone.isBlank()) {
            throw new ExceptionAdvice(TELEFONE_UBS_OBRIGATORIO);
        }

        return telefone;
    }

    private String validaNome(String nome) {

        if (isNull(nome) || nome.isBlank()) {
            throw new ExceptionAdvice(NOME_UBS_OBRIGATORIO);
        }

        return nome;
    }

    public void atualiza(UbsRequest request) {

        this.nome = validaNome(request.nome());
        this.telefone = validaTelefone(request.telefone());
        this.inicioAtendimento = validaInicioAtendimento(request.inicioAtendimento());
        this.fimAtendimento = validaFimAtendimento(request.fimAtendimento());
        this.endereco.atualiza(request.endereco());
    }

    public boolean temMedicamentoDisponivel(Medicamento medicamento, BigInteger quantidade) {
        return estoque.stream()
                .filter(e -> e.getMedicamento().equals(medicamento))
                .map(Estoque::getQuantidade)
                .reduce(BigInteger.ZERO, BigInteger::add)
                .compareTo(quantidade) >= 0;
    }
}
