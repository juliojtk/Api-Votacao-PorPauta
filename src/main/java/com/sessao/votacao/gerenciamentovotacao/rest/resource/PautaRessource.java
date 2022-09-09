package com.sessao.votacao.gerenciamentovotacao.rest.resource;

import com.sessao.votacao.gerenciamentovotacao.domain.dtos.PautaDto;
import com.sessao.votacao.gerenciamentovotacao.domain.entities.Pauta;
import com.sessao.votacao.gerenciamentovotacao.rest.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pauta")
public class PautaRessource {

    @Autowired
    private final PautaService pautaService;

    @GetMapping("/listar")
    public String teste(){
        return "Ok pauta";
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Pauta savePauta(@RequestBody PautaDto pautaDto){
        return pautaService.persitirPauta(pautaDto);
    }

}
