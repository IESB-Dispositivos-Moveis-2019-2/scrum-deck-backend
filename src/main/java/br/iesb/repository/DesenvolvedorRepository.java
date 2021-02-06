package br.iesb.repository;

import br.iesb.model.entity.Desenvolvedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DesenvolvedorRepository extends JpaRepository<Desenvolvedor, Long> {

    boolean existsByEmail(String email);

    Optional<Desenvolvedor> findByEmail(String email);
}
