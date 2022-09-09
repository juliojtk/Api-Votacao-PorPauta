package com.sessao.votacao.gerenciamentovotacao.rest.service.impl;

import com.sessao.votacao.gerenciamentovotacao.domain.dtos.PautaDto;
import com.sessao.votacao.gerenciamentovotacao.domain.entities.Associado;
import com.sessao.votacao.gerenciamentovotacao.domain.entities.Pauta;
import com.sessao.votacao.gerenciamentovotacao.domain.repositories.AssociadoRepository;
import com.sessao.votacao.gerenciamentovotacao.domain.repositories.PautaRepository;
import com.sessao.votacao.gerenciamentovotacao.rest.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PautaServiceImpl implements PautaService {

    @Autowired
    private final PautaRepository pautaRepository;

    @Autowired
    private final AssociadoRepository associadoRepository;

    @Override
    public Pauta persitirPauta(PautaDto pautaDto) {
        return null;
    }

}
