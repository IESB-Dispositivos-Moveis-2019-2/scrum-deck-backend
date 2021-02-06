package br.iesb.repository;

import br.iesb.model.entity.Estoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoriaRepository extends JpaRepository<Estoria, Long> {

    @Query("SELECT e FROM Estoria e WHERE e.sprint.id = :idSprint ORDER BY e.nome")
    List<Estoria> recuperarPorSprint(@Param("idSprint") Long idSprint);

}
