package com.sessao.votacao.gerenciamentovotacao.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sessao.votacao.gerenciamentovotacao.domain.enums.Voto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "assunto", length = 200)
    private String assunto;

    @Enumerated(EnumType.STRING)
    @Column(name = "votos")
    private Voto votos;

    private String resultado;

    private Integer idAssociado;

//    @JsonIgnore
//    @OneToMany(mappedBy = "pauta", fetch = FetchType.LAZY) // Uma Pauta para muitos Associados
//    private Set<Associado> associados;
}
