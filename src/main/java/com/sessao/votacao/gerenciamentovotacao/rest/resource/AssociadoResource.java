package com.sessao.votacao.gerenciamentovotacao.rest.resource;

import com.sessao.votacao.gerenciamentovotacao.domain.dtos.AssociadosDto;
import com.sessao.votacao.gerenciamentovotacao.domain.entities.Associado;
import com.sessao.votacao.gerenciamentovotacao.rest.service.AssociadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@Api("Api dos Associados")
@RequestMapping("/api/associado")
public class AssociadoResource {

    @Autowired
    AssociadoService associadoService;

    @GetMapping()
    @ResponseStatus(OK)
    @ApiOperation("Buscar todos os Associados")
    @ApiResponse(code = 200, message = "Associados encontrados")
    public List<Associado> buscarTodosAssociados(){
        return associadoService.listarTodosAssociados();
    }

    @PostMapping("/gerar-resultado-votacao/{pautaId}")
    @ResponseStatus(CREATED)
    @ApiOperation("Gerar resultado da votação.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Votação Gerada"),
            @ApiResponse(code = 400, message = "Erro de validação.")
    })
    public void gerarResultadoVotacao(@PathVariable Integer pautaId){
        associadoService.persistirResultadoVotacao(pautaId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    @ApiOperation("Buscar Associado por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Associado encontrado"),
            @ApiResponse(code = 404, message = "Associado não encontrado para o ID informado.")
    })
    public Associado buscarAssociadoPorId(@PathVariable Integer id){
        return associadoService.ListarAssociadoId(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Salva um Associado com seu Voto")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Associado e Voto salvo com sucesso!"),
            @ApiResponse(code = 400, message = "Erro de validação.")
    })
    public void salvarAssociadoEVoto(@Valid @RequestBody AssociadosDto associadosDto){
        associadoService.persistirAssociadoEVotar(associadosDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Atualiza dados do Associado")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associado atualizado"),
            @ApiResponse(code = 400, message = "Associado não encontrado para o ID informado.")
    })
    public void alterarAssociado(@Valid @PathVariable Integer id, @RequestBody Associado associado){
        associadoService.atualizarAssociado(id, associado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Deleta um Associado")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Associado deletado com sucesso"),
            @ApiResponse(code = 404, message = "Associado não encontrado para o ID informado.")
    })
    public void removerAssociado(@PathVariable Integer id){
        associadoService.deletarAssociado(id);
    }
}
