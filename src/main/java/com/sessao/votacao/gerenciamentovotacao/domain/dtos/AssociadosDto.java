package com.sessao.votacao.gerenciamentovotacao.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssociadosDto {

    private String nome;

    private String cpf;

    private String voto;

    private Integer pautaId;

}
