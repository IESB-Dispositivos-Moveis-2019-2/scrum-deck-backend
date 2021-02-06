package br.iesb.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "sprint")
public class Sprint extends BaseEntity<Long> {
    private static final long serialVersionUID = -535037529850093915L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "link")
    private String link;

    @OneToMany(mappedBy = "sprint", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Estoria> estorias;

}
