package br.iesb.service;

import br.iesb.model.dto.entrada.CadastroEstoriaDTO;
import br.iesb.model.dto.saida.EstoriaDTO;
import br.iesb.model.entity.Voto;
import br.iesb.model.mapper.EstoriaMapper;
import br.iesb.repository.EstoriaRepository;
import br.iesb.repository.SprintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static br.iesb.message.BusinessMessageProperty.ESTORIA_SPRINT_NAO_ENCONTRADA;
import static br.iesb.message.CoreMessageProperty.API_RESOURCE_NOTFOUND;

@Service
@RequiredArgsConstructor
public class EstoriaService {

    private final EstoriaMapper mapper;
    private final EstoriaRepository repository;
    private final SprintRepository sprintRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public EstoriaDTO cadastrar(CadastroEstoriaDTO cadastroEstoriaDTO) {
        final var sprint = sprintRepository
                .findById(cadastroEstoriaDTO.getIdSprint())
                .orElseThrow(ESTORIA_SPRINT_NAO_ENCONTRADA::businessException);

        var entity = mapper.fromRegister(cadastroEstoriaDTO);
        entity.setSprint(sprint);
        entity = repository.save(entity);

        final var dto = mapper.toDto(entity);
        dto.setIdSprint(sprint.getId());
        return dto;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw API_RESOURCE_NOTFOUND.resourceNotFoundException();
        }
        repository.deleteById(id);
    }

    public EstoriaDTO recuperar(Long id) {
        final var entity = repository.findById(id).orElseThrow(API_RESOURCE_NOTFOUND::resourceNotFoundException);
        final var dto = mapper.toDto(entity);
        dto.setIdSprint(entity.getSprint().getId());
        return dto;
    }

    public List<EstoriaDTO> recuperarPorSprint(Long idSprint) {
        if (!sprintRepository.existsById(idSprint)) {
            throw ESTORIA_SPRINT_NAO_ENCONTRADA.businessException();
        }
        final var entities = repository.recuperarPorSprint(idSprint);
        final var dtos = mapper.toDto(entities);
        dtos.forEach(d -> d.setIdSprint(idSprint));
        return dtos;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Integer encerrarVotacao(Long id) {
        var entity = repository.findById(id).orElseThrow(API_RESOURCE_NOTFOUND::resourceNotFoundException);
        if (!CollectionUtils.isEmpty(entity.getVotos())) {
            final var soma = entity.getVotos().stream().map(Voto::getPontos).reduce(Integer::sum).orElse(0);
            final var media = soma / entity.getVotos().size();
            entity.setPontuacao(media);
        }else {
            entity.setPontuacao(0);
        }
        entity = repository.save(entity);
        return entity.getPontuacao();
    }

}
