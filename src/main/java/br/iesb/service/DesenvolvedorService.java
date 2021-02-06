package br.iesb.service;

import br.iesb.model.dto.entrada.CadastroDesenvolvedorDTO;
import br.iesb.model.dto.saida.DesenvolvedorDTO;
import br.iesb.model.mapper.DesenvolvedorMapper;
import br.iesb.repository.DesenvolvedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static br.iesb.message.BusinessMessageProperty.DESENVOLVEDOR_EMAIL_JA_CADASTRADO;
import static br.iesb.message.CoreMessageProperty.API_RESOURCE_NOTFOUND;

@Service
@RequiredArgsConstructor
public class DesenvolvedorService {

    private final DesenvolvedorMapper mapper;
    private final DesenvolvedorRepository repository;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public DesenvolvedorDTO cadastrar(CadastroDesenvolvedorDTO cadastroDesenvolvedorDTO) {
        if (repository.existsByEmail(cadastroDesenvolvedorDTO.getEmail())) {
            throw DESENVOLVEDOR_EMAIL_JA_CADASTRADO.businessException();
        }
        var entity = mapper.fromRegister(cadastroDesenvolvedorDTO);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }


    public DesenvolvedorDTO recuperar(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(API_RESOURCE_NOTFOUND::resourceNotFoundException));
    }

    public DesenvolvedorDTO recuperar(String email) {
        return mapper.toDto(repository.findByEmail(email).orElseThrow(API_RESOURCE_NOTFOUND::resourceNotFoundException));
    }

}
