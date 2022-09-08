package com.sessao.votacao.gerenciamentovotacao.rest.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pauta")
public class PautaRessource {

    @GetMapping("/listar")
    public String teste(){
        return "Ok pauta";
    }
}
