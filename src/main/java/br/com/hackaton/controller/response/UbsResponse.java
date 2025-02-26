package br.com.hackaton.controller.response;

import br.com.hackaton.entity.Ubs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UbsResponse {

    private Long id;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    private String nome;

    private String telefone;

    private String inicioAtendimento;

    private String fimAtendimento;

    private EnderecoResponse endereco;

    public UbsResponse(Ubs ubs) {
        this(ubs.getId(), ubs.getDataHoraCriacao(), ubs.getDataHoraAtualizacao(), ubs.getNome(), ubs.getTelefone(),
             ubs.getInicioAtendimento(), ubs.getFimAtendimento(), new EnderecoResponse(ubs.getEndereco()));
    }

}
