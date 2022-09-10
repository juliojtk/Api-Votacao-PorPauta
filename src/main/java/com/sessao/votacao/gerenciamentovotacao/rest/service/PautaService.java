package com.sessao.votacao.gerenciamentovotacao.rest.service;

import com.sessao.votacao.gerenciamentovotacao.domain.dtos.PautaDto;
import com.sessao.votacao.gerenciamentovotacao.domain.entities.Pauta;

import java.util.List;

public interface PautaService {

    Pauta persitirPauta(PautaDto pautaDto);
    List<Pauta> listarTodasPautas();
    Pauta buscarResultPauta(Integer idPauta);
}
