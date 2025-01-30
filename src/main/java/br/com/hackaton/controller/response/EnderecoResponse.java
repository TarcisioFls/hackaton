package br.com.hackaton.controller.response;

import br.com.hackaton.entity.Endereco;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EnderecoResponse {

    private Long id;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    private String latitude;

    private String longitude;

    public EnderecoResponse() {}

    public EnderecoResponse(Long id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String cep, String logradouro,
                            String numero, String complemento, String bairro, String cidade, String estado, String latitude,
                            String longitude) {

        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public EnderecoResponse(Endereco endereco) {
        this(endereco.getId(), endereco.getDataHoraCriacao(), endereco.getDataHoraAtualizacao(), endereco.getCep(),
             endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(),
             endereco.getCidade(), endereco.getEstado(), endereco.getLatitude(), endereco.getLongitude());
    }

}
