package com.sessao.votacao.gerenciamentovotacao.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    @NotEmpty(message = "Campo nome não pode ser vazio")
    private String nome;

    @Column(name = "cpf", length = 11, unique = true)
    @CPF(message = "Campo CPF invalido!")
    @NotEmpty(message = "Campo CPF não pode ser vazio")
    @NotNull
    private String cpf;

    @Column(name = "voto", length = 3)
    @NotEmpty(message = "Campo voto não pode ser vazio")
    @NotNull
    @Pattern(regexp="^(sim|Sim|SIM|nao|Nao|NAO|não|Não|NÃO)$", message="É possivel inserir apenas Sim ou Não")
    private String voto;
    @ManyToOne // Muitas Associados para uma Pauta
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;



}
