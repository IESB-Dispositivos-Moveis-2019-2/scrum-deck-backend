package br.iesb.model.mapper;

import br.iesb.model.dto.entrada.CadastroVotoDTO;
import br.iesb.model.dto.saida.VotoDTO;
import br.iesb.model.entity.Voto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VotoMapper extends BaseMapper<Voto, VotoDTO, CadastroVotoDTO> {

    @InheritInverseConfiguration(name = "toDto")
    void fromDto(VotoDTO dto, @MappingTarget Voto entity);
}
