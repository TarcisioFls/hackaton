package br.com.hackaton.mapper;

import br.com.hackaton.controller.request.MedicamentoRequest;
import br.com.hackaton.controller.response.MedicamentoResponse;
import br.com.hackaton.entity.Medicamento;
import br.com.hackaton.entity.Ubs;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface MedicamentoMapper {

    MedicamentoResponse toResponse(Medicamento medicamento);

    @Mapping(target = "id", ignore = true)
    Medicamento toEntity(MedicamentoRequest request, Ubs ubs);

}
