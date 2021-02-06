package br.iesb.service;

import br.iesb.model.dto.entrada.CadastroSprintDTO;
import br.iesb.model.dto.saida.SprintDTO;
import br.iesb.model.mapper.SprintMapper;
import br.iesb.repository.SprintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.iesb.message.CoreMessageProperty.API_RESOURCE_NOTFOUND;

@Service
@RequiredArgsConstructor
public class SprintService {

    private final SprintMapper mapper;
    private final SprintRepository repository;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SprintDTO cadastrar(CadastroSprintDTO cadastroSprintDTO) {
        var entity = mapper.fromRegister(cadastroSprintDTO);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw API_RESOURCE_NOTFOUND.resourceNotFoundException();
        }
        repository.deleteById(id);
    }

    public SprintDTO recuperarPorId(Long id) {
        final var entity = repository.findById(id).orElseThrow(API_RESOURCE_NOTFOUND::resourceNotFoundException);
        return mapper.toDto(entity);
    }

    public List<SprintDTO> recuperarTodos() {
        return mapper.toDto(repository.findAll());
    }
}
