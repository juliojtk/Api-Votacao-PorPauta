package com.sessao.votacao.gerenciamentovotacao.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultadoVotacaoDto {

    private Integer pautaId;
    private String voto;
    private Integer qtdVotos;

}
