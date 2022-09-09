package com.sessao.votacao.gerenciamentovotacao.rest.resource;

import com.sessao.votacao.gerenciamentovotacao.domain.dtos.AssociadosDto;
import com.sessao.votacao.gerenciamentovotacao.domain.entities.Associado;
import com.sessao.votacao.gerenciamentovotacao.rest.service.AssociadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/associado")
public class AssociadoResource {

    @Autowired
    AssociadoService associadoService;

    @GetMapping()
    @ResponseStatus(OK)
    public List<Associado> buscarTodosAssociados(){
        return associadoService.listarTodosAssociados();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Associado buscarAssociadoPorId(@PathVariable Integer id){
        return associadoService.ListarAssociadoId(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Associado salvarAssociado(@RequestBody AssociadosDto associadosDto){
        return associadoService.persistirAssociado(associadosDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void alterarAssociado(@PathVariable Integer id, @RequestBody Associado associado){
        associadoService.atualizarAssociado(id, associado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void removerAssociado(@PathVariable Integer id){
        associadoService.deletarAssociado(id);
    }
}
