package com.sessao.votacao.gerenciamentovotacao.rest.service.impl;

import com.sessao.votacao.gerenciamentovotacao.domain.dtos.AssociadosDto;
import com.sessao.votacao.gerenciamentovotacao.domain.dtos.ResultadoVotacaoDto;
import com.sessao.votacao.gerenciamentovotacao.domain.entities.Associado;
import com.sessao.votacao.gerenciamentovotacao.domain.entities.Pauta;
import com.sessao.votacao.gerenciamentovotacao.domain.repositories.AssociadoRepository;
import com.sessao.votacao.gerenciamentovotacao.domain.repositories.PautaRepository;
import com.sessao.votacao.gerenciamentovotacao.exceptions.GerenciamentoException;
import com.sessao.votacao.gerenciamentovotacao.rest.service.AssociadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AssociadoServiceImpl implements AssociadoService {

    @Autowired
    private final AssociadoRepository associadoRepository;

    @Autowired
    private final PautaRepository pautaRepository;

    private static final String PAUTA_NAO_ENCONTRADO = "Id da Pauta não encontrado";

    @Override
    public List<Associado> listarTodosAssociados() {
        return associadoRepository.findAll();
    }

    @Override
    public Associado ListarAssociadoId(Integer id) {
        Optional<Associado> associado = associadoRepository.findById(id);
        return associado.orElseThrow(() -> new GerenciamentoException("Associado não encontrado"));
    }

    @Override
    public void persistirResultadoVotacao(Integer pautaId) {
        List<ResultadoVotacaoDto> listResultadoVotacaoDto = associadoRepository.findResultadoVotos(pautaId)
            .stream()
            .map(e -> convertToObject(String.valueOf(e))).toList();

            Integer maiorSim = 0;
            Integer maiorNao = 0;

            if (!listResultadoVotacaoDto.isEmpty()){

                for (ResultadoVotacaoDto r : listResultadoVotacaoDto){
                    if (r.getVoto().equals("SIM")){
                        maiorSim += r.getQtdVotos();
                    }else if (r.getVoto().equals("NAO")){
                        maiorNao += r.getQtdVotos();
                    }
                    if (maiorSim > maiorNao){
                        Integer idPauta = r.getPautaId();
                        Pauta pauta = pautaRepository.findById(idPauta)
                                .orElseThrow(() -> new GerenciamentoException(PAUTA_NAO_ENCONTRADO));
                        pauta.setResultado("Mais votos Sim, quantidade: " + maiorSim + " de um total de: " + (maiorNao + maiorSim) + " votos!");
                        pautaRepository.save(pauta);

                    }else if (maiorNao > maiorSim){
                        Integer idPauta = r.getPautaId();
                        Pauta pauta = pautaRepository.findById(idPauta)
                                .orElseThrow(() -> new GerenciamentoException(PAUTA_NAO_ENCONTRADO));
                        pauta.setResultado("Mais votos Nao, quantidade: " + maiorNao + " de um total de: " + (maiorNao + maiorSim) + " votos!");
                        pautaRepository.save(pauta);

                    }else {
                        Integer idPauta = r.getPautaId();
                        Pauta pauta = pautaRepository.findById(idPauta)
                                .orElseThrow(() -> new GerenciamentoException(PAUTA_NAO_ENCONTRADO));
                        pauta.setResultado("Votos empatados, quantidade Sim: " + maiorSim + " Quantidade Não: " + maiorNao);
                        pautaRepository.save(pauta);
                    }
                }
        }
    }


    public ResultadoVotacaoDto convertToObject(String element){ // Convertendo List<String> para List<ResultadoVotacaoDto>
        String[] strResult = element.split(",");
        return new ResultadoVotacaoDto(Integer.parseInt(strResult[0]), strResult[1], Integer.parseInt(strResult[2]));
    }

    @Override
    public void persistirAssociadoEVotar(AssociadosDto associadosDto) {
        Integer idPauta = associadosDto.getPautaId();
        Pauta p = pautaRepository
                .findById(idPauta)
                .orElseThrow(() -> new GerenciamentoException(PAUTA_NAO_ENCONTRADO));

        Associado associado = new Associado();
            associado.setNome(associadosDto.getNome());
            associado.setCpf(associadosDto.getCpf());
            associado.setVoto(associadosDto.getVoto().toUpperCase());
            associado.setPauta(p);

        associadoRepository.save(associado);
    }

    @Override
    public void atualizarAssociado(Integer id, Associado associado) {
        associadoRepository
                .findById(id)
                .map(associadoExiste -> {
                    associado.setId(associadoExiste.getId());
                    associado.setPauta(associadoExiste.getPauta());
                    associado.setVoto(associado.getVoto().toUpperCase());
                    associadoRepository.save(associado);
                    return associado;
                });
    }

    @Override
    public void deletarAssociado(Integer id) {
        associadoRepository.deleteById(id);
    }


}
