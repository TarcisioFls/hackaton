package br.com.hackaton.entity;

import br.com.hackaton.controller.request.EnderecoRequest;
import br.com.hackaton.exception.ExceptionAdvice;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

import static br.com.hackaton.exception.CodigoError.BAIRRO_ENDERECO_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.CIDADE_ENDERECO_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.ESTADO_ENDERECO_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.LATITUDE_ENDERECO_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.LONGITUDE_ENDERECO_OBRIGATORIO;
import static java.util.Objects.isNull;

@Getter
@Entity
@Table(name = "endereco")
public class Endereco extends BaseEntity {

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;

    public Endereco() {}

    public Endereco(String cep, String logradouro, String numero, String complemento, String bairro,
                   String cidade, String estado, String latitude, String longitude) {

        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = validaBairro(bairro);
        this.cidade = validaCidade(cidade);
        this.estado = validaEstado(estado);
        this.latitude = validaLatitude(latitude);
        this.longitude = validaLongitude(longitude);
    }

    public Endereco(EnderecoRequest request) {
        this(request.cep(), request.logradouro(), request.numero(), request.complemento(), request.bairro(), request.cidade(),
                request.estado(), request.latitude(), request.longitude());
    }

    public Endereco(Endereco endereco) {
        this(endereco.getCep(), endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(),
                endereco.getCidade(), endereco.getEstado(), endereco.getLatitude(), endereco.getLongitude());
    }

    private String validaLongitude(String longitude) {

        if (isNull(longitude) || longitude.isBlank()) {
            throw new ExceptionAdvice(LONGITUDE_ENDERECO_OBRIGATORIO);
        }

        return longitude;
    }

    private String validaLatitude(String latitude) {

        if (isNull(latitude) || latitude.isBlank()) {
            throw new ExceptionAdvice(LATITUDE_ENDERECO_OBRIGATORIO);
        }

        return latitude;
    }

    private String validaEstado(String estado) {

        if (isNull(estado) || estado.isBlank()) {
            throw new ExceptionAdvice(ESTADO_ENDERECO_OBRIGATORIO);
        }

        return estado;
    }

    private String validaCidade(String cidade) {

        if (isNull(cidade) || cidade.isBlank()) {
            throw new ExceptionAdvice(CIDADE_ENDERECO_OBRIGATORIO);
        }

        return cidade;
    }

    private String validaBairro(String bairro) {

        if (isNull(bairro) || bairro.isBlank()) {
            throw new ExceptionAdvice(BAIRRO_ENDERECO_OBRIGATORIO);
        }

        return bairro;
    }

    public void atualiza(EnderecoRequest endereco) {

        this.cep = endereco.cep();
        this.logradouro = endereco.logradouro();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.bairro = validaBairro(endereco.bairro());
        this.cidade = validaCidade(endereco.cidade());
        this.estado = validaEstado(endereco.estado());
        this.latitude = validaLatitude(endereco.latitude());
        this.longitude = validaLongitude(endereco.longitude());
    }
}
