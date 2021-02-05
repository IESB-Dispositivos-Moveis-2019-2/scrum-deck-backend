package br.iesb.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "voto")
public class Voto extends BaseEntity<Voto.PK> {
    private static final long serialVersionUID = 3967325656706424669L;

    @EmbeddedId
    @AttributeOverride(name = "idEstoria", column = @Column(name = "id_estoria", nullable = false))
    @AttributeOverride(name = "idDesenvolvedor", column = @Column(name = "id_desenvolvedor", nullable = false))
    private PK id;

    @Column(name = "voto", nullable = false)
    private Integer voto;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estoria", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    private Estoria estoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_desenvolvedor", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    private Desenvolvedor desenvolvedor;

    @Data
    @Embeddable
    public static class PK implements Serializable {
        private static final long serialVersionUID = 7684758573831407666L;

        private Long idEstoria;
        private Long idDesenvolvedor;
    }

}
