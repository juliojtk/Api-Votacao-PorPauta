package com.sessao.votacao.gerenciamentovotacao.rest.resource;

import com.sessao.votacao.gerenciamentovotacao.domain.dtos.PautaDto;
import com.sessao.votacao.gerenciamentovotacao.domain.entities.Pauta;
import com.sessao.votacao.gerenciamentovotacao.rest.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pauta")
public class PautaRessource {

    @Autowired
    private final PautaService pautaService;

    @GetMapping()
    @ResponseStatus(OK)
    public List<Pauta> buscarTodasPautas(){
        return pautaService.listarTodasPautas();
    }

    @GetMapping("/visualizar-votacao/{idPauta}")
    @ResponseStatus(OK)
    public Pauta visualizarResultadoVotacao(@PathVariable Integer idPauta){
        return pautaService.buscarResultPauta(idPauta);
    }

    @PostMapping()
    @ResponseStatus(CREATED)
    public Pauta salvarPauta(@Valid @RequestBody PautaDto pautaDto){
        return pautaService.persitirPauta(pautaDto);
    }

}
