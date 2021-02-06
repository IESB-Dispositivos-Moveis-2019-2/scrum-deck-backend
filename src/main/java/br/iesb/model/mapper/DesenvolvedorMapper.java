package br.iesb.model.mapper;

import br.iesb.model.dto.entrada.CadastroDesenvolvedorDTO;
import br.iesb.model.dto.saida.DesenvolvedorDTO;
import br.iesb.model.entity.Desenvolvedor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DesenvolvedorMapper extends BaseMapper<Desenvolvedor, DesenvolvedorDTO, CadastroDesenvolvedorDTO> {

    @InheritInverseConfiguration(name = "toDto")
    void fromDto(DesenvolvedorDTO dto, @MappingTarget Desenvolvedor entity);

}
