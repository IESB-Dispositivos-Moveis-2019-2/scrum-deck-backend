package br.iesb.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "estoria")
public class Estoria extends BaseEntity<Long> {
    private static final long serialVersionUID = -1055051600529365347L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sprint", referencedColumnName = "id", nullable = false)
    private Sprint sprint;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "link")
    private String link;

    @Column(name = "pontuacao")
    private Integer pontuacao;

    @OneToMany(mappedBy = "estoria", fetch = FetchType.LAZY)
    private List<Voto> votos;
}
