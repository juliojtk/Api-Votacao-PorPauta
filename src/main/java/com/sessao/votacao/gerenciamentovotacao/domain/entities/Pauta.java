package com.sessao.votacao.gerenciamentovotacao.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private String resultado;

    @JsonIgnore
    @OneToMany(mappedBy = "pautaId", fetch = FetchType.LAZY) // Uma Pauta para muitos Associados
    private Set<Associado> associados;
}
