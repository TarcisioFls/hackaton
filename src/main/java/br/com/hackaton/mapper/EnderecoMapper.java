//package br.com.hackaton.mapper;
//
//import br.com.hackaton.config.MappingConfig;
//import br.com.hackaton.controller.request.EnderecoRequest;
//import br.com.hackaton.controller.response.EnderecoResponse;
//import br.com.hackaton.entity.Endereco;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(config = MappingConfig.class)
//public interface EnderecoMapper {
//
//    EnderecoResponse toResponse(Endereco endereco);
//
//    @Mapping(target = "id", ignore = true)
//    Endereco toEntity(EnderecoRequest request);
//
//}
