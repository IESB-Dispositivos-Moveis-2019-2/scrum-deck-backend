package br.iesb.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "sprint")
public class Sprint extends BaseEntity<Long> {
    private static final long serialVersionUID = 1702768023669833410L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "link")
    private String link;

    @OneToMany(mappedBy = "sprint", fetch = FetchType.LAZY)
    private List<Estoria> estorias;

}
