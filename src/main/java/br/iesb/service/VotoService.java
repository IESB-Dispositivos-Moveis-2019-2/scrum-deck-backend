package br.iesb.service;

import br.iesb.model.dto.entrada.CadastroVotoDTO;
import br.iesb.model.dto.saida.VotoDTO;
import br.iesb.model.entity.Voto;
import br.iesb.model.mapper.VotoMapper;
import br.iesb.repository.DesenvolvedorRepository;
import br.iesb.repository.EstoriaRepository;
import br.iesb.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static br.iesb.message.BusinessMessageProperty.VOTO_DESENVOLVEDOR_NAO_ENCONTRADO;
import static br.iesb.message.BusinessMessageProperty.VOTO_ESTORIA_NAO_ENCONTRADA;

@Service
@RequiredArgsConstructor
public class VotoService {

    private final VotoMapper mapper;
    private final VotoRepository repository;

    private final EstoriaRepository estoriaRepository;
    private final DesenvolvedorRepository desenvolvedorRepository;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public VotoDTO cadastrar(CadastroVotoDTO cadastroVotoDTO) {
        final var estoria = estoriaRepository
                .findById(cadastroVotoDTO.getIdEstoria())
                .orElseThrow(VOTO_ESTORIA_NAO_ENCONTRADA::resourceNotFoundException);

        final var desenvolvedor = desenvolvedorRepository
                .findById(cadastroVotoDTO.getIdDesenvolvedor())
                .orElseThrow(VOTO_DESENVOLVEDOR_NAO_ENCONTRADO::resourceNotFoundException);

        final var pk = Voto.PK.builder().idEstoria(cadastroVotoDTO.getIdEstoria()).idDesenvolvedor(cadastroVotoDTO.getIdDesenvolvedor()).build();
        var voto = repository.findById(pk).orElse(mapper.fromRegister(cadastroVotoDTO));
        voto.setId(pk);
        voto.setEstoria(estoria);
        voto.setDesenvolvedor(desenvolvedor);
        voto.setDataHora(LocalDateTime.now());
        voto.setPontos(cadastroVotoDTO.getPontos());

        voto = repository.save(voto);

        final var dto = mapper.toDto(voto);
        dto.setIdEstoria(voto.getId().getIdEstoria());
        dto.setIdDesenvolvedor(voto.getId().getIdDesenvolvedor());

        return dto;
    }

}
