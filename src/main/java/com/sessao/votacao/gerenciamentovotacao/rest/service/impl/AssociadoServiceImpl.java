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

        Pauta p = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new GerenciamentoException(PAUTA_NAO_ENCONTRADO));

            Integer maiorSim = 0;
            Integer maiorNao = 0;

            if (!listResultadoVotacaoDto.isEmpty()){
                for (ResultadoVotacaoDto r : listResultadoVotacaoDto){

                    if (r.getVoto().equals("SIM")){
                        maiorSim += r.getQtdVotos();
                    }else if (r.getVoto().equals("NAO") || r.getVoto().equals("NÃO")){
                        maiorNao += r.getQtdVotos();
                    }
                    if (maiorSim > maiorNao){
                        p.setResultado("Mais votos Sim, quantidade: " + maiorSim + " de um total de: " + (maiorNao + maiorSim) + " votos!");
                        pautaRepository.save(p);

                    }else if (maiorNao > maiorSim){
                        p.setResultado("Mais votos Nao, quantidade: " + maiorNao + " de um total de: " + (maiorNao + maiorSim) + " votos!");
                        pautaRepository.save(p);

                    }else {
                        p.setResultado("Votos empatados, quantidade Sim: " + maiorSim + " Quantidade Não: " + maiorNao);
                        pautaRepository.save(p);
                    }
            }
        }
    }

    private ResultadoVotacaoDto convertToObject(String element){ // Convertendo List<String> para List<ResultadoVotacaoDto>
        String[] strResult = element.split(",");
        return new ResultadoVotacaoDto(Integer.parseInt(strResult[0]), strResult[1], Integer.parseInt(strResult[2]));
    }

    @Override
    public void persistirAssociadoEVotar(AssociadosDto associadosDto) {
        Integer idPauta = associadosDto.getPautaId();
        Pauta p = pautaRepository
                .findById(idPauta)
                .orElseThrow(() -> new GerenciamentoException(PAUTA_NAO_ENCONTRADO));

        if (associadoRepository.findByCpf(associadosDto.getCpf()).isPresent()){
            throw new GerenciamentoException("O Cpf informado já se encontra na base de dados!");
        }else {
            Associado associado = new Associado();
            associado.setNome(associadosDto.getNome());
            associado.setCpf(associadosDto.getCpf());
            associado.setVoto(associadosDto.getVoto().toUpperCase());
            associado.setPauta(p);

            associadoRepository.save(associado);
        }
    }

    @Override
    public void atualizarAssociado(Integer id, Associado associado) {
        associadoRepository.findById(id)
                .orElseThrow(() -> new GerenciamentoException("Id de associado não existe!"));

            associadoRepository
                .findByIdAndCpf(id, associado.getCpf())
                .map(associadoExiste -> {
                    associado.setId(associadoExiste.getId());
                    associado.setPauta(associadoExiste.getPauta());
                    associado.setVoto(associado.getVoto().toUpperCase());
                    associadoRepository.save(associado);
                    return associado;
                }).orElseThrow(() -> new GerenciamentoException("Cpf já existe na base de dados ou está invalido!"));
    }

    @Override
    public void deletarAssociado(Integer id) {
        associadoRepository.findById(id)
            .orElseThrow(() -> new GerenciamentoException("Id associado não encontrado!"));
        associadoRepository.deleteById(id);
    }

}
