package br.iesb.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "estoria")
public class Estoria extends BaseEntity<Long> {
    private static final long serialVersionUID = -7479330826942732683L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sprint", referencedColumnName = "id", nullable = false)
    private Sprint sprint;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "link")
    private String link;

    @Column(name = "pontuacao")
    private Integer pontuacao;

    @OneToMany(mappedBy = "estoria", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Voto> votos;
}
