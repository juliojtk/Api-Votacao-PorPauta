package com.sessao.votacao.gerenciamentovotacao.rest.service.impl;

import com.sessao.votacao.gerenciamentovotacao.domain.dtos.AssociadosDto;
import com.sessao.votacao.gerenciamentovotacao.domain.entities.Associado;
import com.sessao.votacao.gerenciamentovotacao.domain.entities.Pauta;
import com.sessao.votacao.gerenciamentovotacao.domain.repositories.AssociadoRepository;
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

    @Override
    public List<Associado> listarTodosAssociados() {
        return associadoRepository.findAll();
    }

    @Override
    public Associado ListarAssociadoId(Integer id) {
        Optional<Associado> associado = associadoRepository.findById(id);
        return associado.orElseThrow(() -> new RuntimeException("Associado não encontrado"));
    }

    @Override
    public Associado persistirAssociado(AssociadosDto associadosDto) {

        Associado associado = new Associado();
            associado.setNome(associadosDto.getNome());
            associado.setCpf(associadosDto.getCpf());

        return associadoRepository.save(associado);
    }

    @Override
    public void atualizarAssociado(Integer id, Associado associado) {
        associadoRepository
                .findById(id)
                .map(associadoExiste -> {
                    associado.setId(associadoExiste.getId());
                    associadoRepository.save(associado);
                    return associado;
                });
    }

    @Override
    public void deletarAssociado(Integer id) {
        associadoRepository.deleteById(id);
    }

}
