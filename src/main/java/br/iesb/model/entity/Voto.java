package br.iesb.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "voto")
public class Voto extends BaseEntity<Voto.PK> {
    private static final long serialVersionUID = 793060946995475041L;

    @EmbeddedId
    @AttributeOverride(name = "idEstoria", column = @Column(name = "id_estoria", nullable = false))
    @AttributeOverride(name = "idDesenvolvedor", column = @Column(name = "id_desenvolvedor", nullable = false))
    private PK id;

    @Column(name = "pontos", nullable = false)
    private Integer pontos;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estoria", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    private Estoria estoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_desenvolvedor", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    private Desenvolvedor desenvolvedor;

    @Data
    @Builder
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PK implements Serializable {
        private static final long serialVersionUID = -3604370789197024307L;

        private Long idEstoria;
        private Long idDesenvolvedor;
    }

}
