package br.iesb.model.mapper;

import br.iesb.model.dto.entrada.CadastroEstoriaDTO;
import br.iesb.model.dto.saida.EstoriaDTO;
import br.iesb.model.entity.Estoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EstoriaMapper extends BaseMapper<Estoria, EstoriaDTO, CadastroEstoriaDTO> {

    @InheritInverseConfiguration(name = "toDto")
    void fromDto(EstoriaDTO dto, @MappingTarget Estoria entity);

}
