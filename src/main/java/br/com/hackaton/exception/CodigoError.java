package br.com.hackaton.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public enum CodigoError {

    NOME_MEDICAMENTO_OBRIGATORIO("Nome do medicamento é obrigatório", BAD_REQUEST, FALSE),
    QUANTIDADE_MEDICAMENTO_OBRIGATORIO("Quantidade do medicamento é obrigatória", BAD_REQUEST, FALSE),
    QUANTIDADE_MEDICAMENTO_INVALIDO("Quantidade do medicamento é inválida", BAD_REQUEST, FALSE),
    MEDICAMENTO_JA_CADASTRADO("Medicamento já cadastrado", BAD_REQUEST, FALSE),
    MEDICAMENTO_NAO_ENCONTRADO("Medicamento não encontrado", NOT_FOUND, FALSE),

    NOME_UBS_OBRIGATORIO("Nome da UBS é obrigatório", BAD_REQUEST, FALSE),
    TELEFONE_UBS_OBRIGATORIO("Telefone da UBS é obrigatório", BAD_REQUEST, FALSE),
    INICIO_ATENDIMENTO_UBS_OBRIGATORIO("Inicio atendimento da UBS é obrigatório", BAD_REQUEST, FALSE),
    FIM_ATENDIMENTO_UBS_OBRIGATORIO("Fim atendimento da UBS é obrigatório", BAD_REQUEST, FALSE),
    UBS_NAO_ENCONTRADA("UBS não encontrada", NOT_FOUND, FALSE),

    BAIRRO_ENDERECO_OBRIGATORIO("Bairro do endereço é obrigatório", BAD_REQUEST, FALSE),
    CIDADE_ENDERECO_OBRIGATORIO("Cidade do endereço é obrigatório", BAD_REQUEST, FALSE),
    ESTADO_ENDERECO_OBRIGATORIO("Estado do endereço é obrigatório", BAD_REQUEST, FALSE),
    LATITUDE_ENDERECO_OBRIGATORIO("Latitude do endereço é obrigatório", BAD_REQUEST, FALSE),
    LONGITUDE_ENDERECO_OBRIGATORIO("Longitude do endereço é obrigatório", BAD_REQUEST, FALSE),

    ESTOQUE_NAO_ENCONTRADO("Estoque não encontrado", NOT_FOUND, FALSE),
    QUANTIDADE_ADICIONADA_ESTOQUE_NEGATIVA("A quantidade a ser adicionada deve ser maior que zero", BAD_REQUEST, FALSE),
    QUANTIDADE_ESTOQUE_NEGATIVA("A quantidade a ser retirada é maior que a quantidade do estoque", BAD_REQUEST, FALSE),

    CNS_PACIENTE_OBRIGATORIO("CNS do paciente é obrigatório", BAD_REQUEST, FALSE),
    TELEFONE_PACIENTE_OBRIGATORIO("Telefone do paciente é obrigatório", BAD_REQUEST, FALSE),
    CPF_PACIENTE_OBRIGATORIO("CPF do paciente é obrigatório", BAD_REQUEST, FALSE),
    NOME_PACIENTE_OBRIGATORIO("Nome do paciente é obrigatório", BAD_REQUEST, FALSE),

    ERROR_DESCONHECIDO("Erro desconhecido", INTERNAL_SERVER_ERROR, TRUE);

    private final String mensagem;

    private final HttpStatus httpStatus;

    private final int codigo;

    private final boolean exibirError;

    CodigoError(String mensagem, HttpStatus httpStatus, boolean exibirError) {
        this.mensagem = mensagem;
        this.httpStatus = httpStatus;
        this.codigo = httpStatus.value();
        this.exibirError = exibirError;
    }
}
