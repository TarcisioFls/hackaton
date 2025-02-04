package br.com.hackaton.controller.response;

import br.com.hackaton.entity.Ubs;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UbsResponse {

    private Long id;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    private String nome;

    private String telefone;

    private String inicioAtendimento;

    private String fimAtendimento;

    private EnderecoResponse endereco;

    public UbsResponse() {}

    public UbsResponse(Long id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String nome, String telefone,
                       String inicioAtendimento, String fimAtendimento, EnderecoResponse endereco) {

        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.nome = nome;
        this.telefone = telefone;
        this.inicioAtendimento = inicioAtendimento;
        this.fimAtendimento = fimAtendimento;
        this.endereco = endereco;
    }

    public UbsResponse(Ubs ubs) {
        this(ubs.getId(), ubs.getDataHoraCriacao(), ubs.getDataHoraAtualizacao(), ubs.getNome(), ubs.getTelefone(),
             ubs.getInicioAtendimento(), ubs.getFimAtendimento(), new EnderecoResponse(ubs.getEndereco()));
    }

    public UbsResponse(UbsResponse ubsResponse) {
        this(ubsResponse.getId(), ubsResponse.getDataCriacao(), ubsResponse.getDataAtualizacao(), ubsResponse.getNome(),
             ubsResponse.getTelefone(), ubsResponse.getInicioAtendimento(), ubsResponse.getFimAtendimento(),
             new EnderecoResponse(ubsResponse.getEndereco()));
    }
}
