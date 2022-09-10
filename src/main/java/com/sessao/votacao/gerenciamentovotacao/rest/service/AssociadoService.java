package com.sessao.votacao.gerenciamentovotacao.rest.service;

import com.sessao.votacao.gerenciamentovotacao.domain.dtos.AssociadosDto;
import com.sessao.votacao.gerenciamentovotacao.domain.entities.Associado;

import java.util.List;

public interface AssociadoService {


    public List<Associado> listarTodosAssociados();
    public void persistirAssociadoEVotar(AssociadosDto associadosDto);
    Associado ListarAssociadoId(Integer id);
    void atualizarAssociado(Integer id, Associado associado);
    void deletarAssociado(Integer id);
    void persistirResultadoVotacao(Integer pautaId);
}
