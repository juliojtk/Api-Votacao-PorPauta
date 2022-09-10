package com.sessao.votacao.gerenciamentovotacao.rest.resource;

import com.sessao.votacao.gerenciamentovotacao.exceptions.GerenciamentoException;
import com.sessao.votacao.gerenciamentovotacao.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(GerenciamentoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleGerenciamentoException(GerenciamentoException ex){
        String msgError = ex.getMessage();
        return new ApiErrors(msgError);
    }

}
