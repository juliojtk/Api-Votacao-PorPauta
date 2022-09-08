package com.sessao.votacao.gerenciamentovotacao.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PautaDto {


    private String assunto;

    private String votos;

    private Set<Integer> associadosId;



}
