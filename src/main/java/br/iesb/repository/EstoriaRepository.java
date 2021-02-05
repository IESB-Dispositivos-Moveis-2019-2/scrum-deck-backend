package br.iesb.repository;

import br.iesb.model.Estoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoriaRepository extends JpaRepository<Estoria, Long> {

}
