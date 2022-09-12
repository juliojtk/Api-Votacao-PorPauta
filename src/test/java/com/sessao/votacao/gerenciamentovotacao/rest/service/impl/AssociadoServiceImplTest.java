package com.sessao.votacao.gerenciamentovotacao.rest.service.impl;

import com.sessao.votacao.gerenciamentovotacao.domain.dtos.AssociadosDto;
import com.sessao.votacao.gerenciamentovotacao.domain.entities.Associado;
import com.sessao.votacao.gerenciamentovotacao.domain.entities.Pauta;
import com.sessao.votacao.gerenciamentovotacao.domain.repositories.AssociadoRepository;
import com.sessao.votacao.gerenciamentovotacao.domain.repositories.PautaRepository;
import com.sessao.votacao.gerenciamentovotacao.exceptions.GerenciamentoException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Teste referente a classe AssociadoServiceImpl")
@SpringBootTest
class AssociadoServiceImplTest {

    @Autowired
    AssociadoServiceImpl service;

    @MockBean
    AssociadoRepository associadoRepository;

    @MockBean
    PautaRepository pautaRepository;

    private static final String CpfTeste = "55578861091";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Listar todos os associados")
    void listarTodosAssociadosReturSucesso() {
        // ACTION
        List<Associado> response = this.service.listarTodosAssociados();

        // ASSERTIONS
        assertNotNull(response);
    }

    @Test
    @DisplayName("Listar associados passando o Id")
    void listarAssociadoIdReturnSucesso() {
        // ARRANGE
        Associado request = getBuildAssociado();
        when(associadoRepository.findById(anyInt())).thenReturn(Optional.of(new Associado()));

        // ACTION
        Associado respose = this.service.ListarAssociadoId(request.getId());

        // ASSERTIONS
        assertNotNull(respose);
        assertEquals(Associado.class, respose.getClass());
    }

    @Test
    @DisplayName("Associado não encontrado pelo Id")
    void listarAssociadoIdReturnException() {
        // ARRANGE
        Associado request = getBuildAssociado();
        when(associadoRepository.findById(anyInt())).thenReturn(Optional.of(new Associado()));

        // ACTION
        GerenciamentoException gerenciamentoException = assertThrows(GerenciamentoException.class, () ->{
            this.service.ListarAssociadoId(null);
        });

        // ASSERTIONS
        Assertions.assertThat(gerenciamentoException.getMessage()).isEqualTo("Associado não encontrado");
    }

    @Test
    @DisplayName("Fazer a persistir do resultado da votação")
    void persistirResultadoVotacaoReturnSucesso() {
        // ARRANGE
        Pauta pautaRequest = getBuildPauta();
        when(associadoRepository.findResultadoVotos(pautaRequest.getId())).thenReturn(getBuildListVotacao());
        when(pautaRepository.findById(anyInt())).thenReturn(Optional.of(pautaRequest));

        // ACTION
        this.service.persistirResultadoVotacao(pautaRequest.getId());

        // ASSERTIONS
        verify(associadoRepository, times(1)).findResultadoVotos(ArgumentMatchers.anyInt());
        verify(pautaRepository, times(1)).findById(ArgumentMatchers.anyInt());
        verify(pautaRepository, times(3)).save(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("Salvar o associado com seu respectivo voto")
    void persistirAssociadoEVotarReturnSucesso() throws Exception {
        // ARRANGE
        AssociadosDto request = getBuildAssociadosDto();
        request.setCpf(null);
        when(pautaRepository.findById(anyInt())).thenReturn(Optional.ofNullable(getBuildPauta()));
        when(associadoRepository.findByCpf(anyString())).thenReturn(Optional.ofNullable(CpfTeste));

        // ACTION
        this.service.persistirAssociadoEVotar(request);

        // ASSERTIONS
        verify(pautaRepository, times(1)).findById(ArgumentMatchers.anyInt());
        verify(associadoRepository, times(1)).save(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("Retorno de Cpf contendo na base de dados")
    void persistirAssociadoEVotarReturnExceptioCpf() {
        // ARRANGE
        AssociadosDto request = getBuildAssociadosDto();
        when(pautaRepository.findById(anyInt())).thenReturn(Optional.ofNullable(getBuildPauta()));
        when(associadoRepository.findByCpf(anyString())).thenReturn(Optional.ofNullable(CpfTeste));

        // ACTION
        GerenciamentoException gerenciamentoException = assertThrows(GerenciamentoException.class, () -> {
            this.service.persistirAssociadoEVotar(request);
        });

        // ASSERTIONS
        Assertions.assertThat(gerenciamentoException.getMessage()).isEqualTo("O Cpf informado já se encontra na base de dados!");
    }

    @Test
    @DisplayName("Atualizando Associado com sucesso")
    void atualizarAssociadoRetornoSucesso() {
        // ARRANGE
        Associado request = getBuildAssociado();
        when(associadoRepository.findById(anyInt())).thenReturn(Optional.of(request));
        when(associadoRepository.findByIdAndCpf(request.getId(), request.getCpf())).thenReturn(Optional.of(request));

        // ACTION
        this.service.atualizarAssociado(request.getId(), request);

        // ASSERTIONS
        verify(associadoRepository, times(1)).findById(ArgumentMatchers.anyInt());
        verify(associadoRepository,times(1)).findByIdAndCpf(ArgumentMatchers.anyInt(), ArgumentMatchers.anyString());
    }

    @Test
    @DisplayName("Deletando Associado com sucesso")
    void deletarAssociadoRetornoSucesso() {
        // ARRANGE
        when(associadoRepository.findById(anyInt())).thenReturn(Optional.of(new Associado()));

        // ACTION
        this.associadoRepository.deleteById(ArgumentMatchers.anyInt());

        //ASSERTION
        verify(associadoRepository, times(1)).deleteById(ArgumentMatchers.anyInt());
    }

    @Test
    @DisplayName("Ao deletar não é encontrado o Id do associado")
    void deletarAssociadoRetornoException() {
        // ACTION
        GerenciamentoException gerenciamentoException = assertThrows(GerenciamentoException.class, () -> {
            this.service.deletarAssociado(null);
        });

        // ASSERTIONS
        Assertions.assertThat(gerenciamentoException.getMessage()).isEqualTo("Id associado não encontrado!");
    }

    private Associado getBuildAssociado(){
        return Associado.builder()
            .id(1)
            .nome("julio")
            .cpf("71375589091")
            .voto("Sim")
            .pauta(new Pauta()).build();
    }

    private Pauta getBuildPauta(){
        return Pauta.builder()
            .id(1)
            .assunto("Diminuir Imposto Teste")
            .resultado("N/D")
            .associados(Collections.singletonList(getBuildAssociado()))
            .build();
    }

    private List<String> getBuildListVotacao(){
        String a = "1,Sim,1";
        String b = "2,Não,1";
        String c = "3,Sim,1";
        return List.of(a, b, c);
    }

    private AssociadosDto getBuildAssociadosDto(){
        return AssociadosDto.builder()
            .nome("julio teste")
            .cpf("69167646000")
            .voto("Sim")
            .pautaId(1)
            .build();
    }
}