package com.sessao.votacao.gerenciamentovotacao.rest.service;


import com.sessao.votacao.gerenciamentovotacao.domain.dtos.PautaDto;
import com.sessao.votacao.gerenciamentovotacao.domain.entities.Pauta;

public interface PautaService {

    Pauta persitirPauta(PautaDto pautaDto);
}
