package com.sessao.votacao.gerenciamentovotacao.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sessao.votacao.gerenciamentovotacao.domain.enums.Voto;
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
@Table(name = "associado")
public class Associado{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "voto", length = 5)
    private String voto;

    @ManyToOne // Muitas Associados para uma Pauta
    @JoinColumn(name = "pauta_id")
    private Pauta pautaId;



}
