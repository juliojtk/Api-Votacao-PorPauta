package com.sessao.votacao.gerenciamentovotacao.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pauta")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "assunto", length = 200)
    @NotEmpty(message = "Campo assunto obrigatorio")
    private String assunto;

    private String resultado;

    @JsonIgnore
    @OneToMany(mappedBy = "pauta", fetch = FetchType.LAZY) // Uma Pauta para muitos Associados
    private List<Associado> associados;
}
