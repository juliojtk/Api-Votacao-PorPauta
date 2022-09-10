package com.sessao.votacao.gerenciamentovotacao.rest.service.impl;

import com.sessao.votacao.gerenciamentovotacao.domain.dtos.PautaDto;
import com.sessao.votacao.gerenciamentovotacao.domain.entities.Pauta;
import com.sessao.votacao.gerenciamentovotacao.domain.repositories.AssociadoRepository;
import com.sessao.votacao.gerenciamentovotacao.domain.repositories.PautaRepository;
import com.sessao.votacao.gerenciamentovotacao.exceptions.GerenciamentoException;
import com.sessao.votacao.gerenciamentovotacao.rest.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PautaServiceImpl implements PautaService {

    @Autowired
    private final PautaRepository pautaRepository;

    @Autowired
    private final AssociadoRepository associadoRepository;

    @Override
    public List<Pauta> listarTodasPautas() {
        return pautaRepository.findAll();
    }

    @Override
    public Pauta buscarResultPauta(Integer idPauta) {
        return pautaRepository.findById(idPauta)
                .orElseThrow(() -> new GerenciamentoException("Id da pauta não encontrado!"));
    }

    @Override
    public Pauta persitirPauta(PautaDto pautaDto) {
        Pauta p = new Pauta();
        p.setAssunto(pautaDto.getAssunto());
        p.setResultado("N/D");
        return pautaRepository.save(p);
    }


}
