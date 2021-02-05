package br.iesb.model.mapper;

import br.iesb.model.dto.entrada.CadastroSprintDTO;
import br.iesb.model.dto.saida.SprintDTO;
import br.iesb.model.entity.Sprint;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SprintMapper extends BaseMapper<Sprint, SprintDTO, CadastroSprintDTO> {

    @InheritInverseConfiguration(name = "toDto")
    void fromDto(SprintDTO dto, @MappingTarget Sprint entity);

}
