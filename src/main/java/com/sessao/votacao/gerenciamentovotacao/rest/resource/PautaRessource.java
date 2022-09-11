package com.sessao.votacao.gerenciamentovotacao.rest.resource;

import com.sessao.votacao.gerenciamentovotacao.domain.dtos.PautaDto;
import com.sessao.votacao.gerenciamentovotacao.domain.entities.Pauta;
import com.sessao.votacao.gerenciamentovotacao.exceptions.GerenciamentoException;
import com.sessao.votacao.gerenciamentovotacao.rest.service.PautaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@Api("Api da Pauta")
@RequestMapping("/api/pauta")
public class PautaRessource {

    @Autowired
    private final PautaService pautaService;

    @GetMapping()
    @ResponseStatus(OK)
    @ApiOperation("Buscar todas as Pautas")
    @ApiResponse(code = 200, message = "Pauta encontrado")
    public List<Pauta> buscarTodasPautas(){
        try {
            return pautaService.listarTodasPautas();
        }catch (Exception e){
            throw new GerenciamentoException(e.getMessage());
        }
    }

    @GetMapping("/visualizar-votacao/{idPauta}")
    @ResponseStatus(OK)
    @ApiOperation("Visualizar resultado da votação")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Resultado encontrado"),
            @ApiResponse(code = 404, message = "Pauta não encontrado para o ID informado.")
    })
    public Pauta visualizarResultadoVotacao(@PathVariable Integer idPauta){
        try {
            return pautaService.buscarResultPauta(idPauta);
        }catch (Exception e){
            throw new GerenciamentoException(e.getMessage());
        }

    }

    @PostMapping()
    @ResponseStatus(CREATED)
    @ApiOperation("Salva uma nova Pauta.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Pauta salva com sucesso!"),
            @ApiResponse(code = 400, message = "Erro de validação.")
    })
    public Pauta salvarPauta(@Valid @RequestBody PautaDto pautaDto){
        try {
            return pautaService.persitirPauta(pautaDto);
        }catch (GerenciamentoException e){
            throw new GerenciamentoException(e.getMessage());
        }
    }

}
