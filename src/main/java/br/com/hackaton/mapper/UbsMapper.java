//package br.com.hackaton.mapper;
//
//import br.com.hackaton.config.MappingConfig;
//import br.com.hackaton.controller.request.UbsRequest;
//import br.com.hackaton.controller.response.UbsResponse;
//import br.com.hackaton.entity.Ubs;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(config = MappingConfig.class, uses = {
//        EnderecoMapper.class,
//        MedicamentoMapper.class
//})
//public interface UbsMapper {
//
//    UbsResponse toResponse(Ubs ubs);
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "medicamentos", ignore = true)
//    Ubs toEntity(UbsRequest request);
//
//}
