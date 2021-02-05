package br.iesb.service;

import br.iesb.repository.SprintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SprintService {

    private final SprintRepository repository;

    public void teste() {
        repository.findAll();
    }

}
