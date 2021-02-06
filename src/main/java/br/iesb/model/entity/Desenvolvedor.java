package br.iesb.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "desenvolvedor")
public class Desenvolvedor extends BaseEntity<Long> {
    private static final long serialVersionUID = 7715509240299416713L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "desenvolvedor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Voto> votos;

}
