package com.sessao.votacao.gerenciamentovotacao.rest.resource;

import com.sessao.votacao.gerenciamentovotacao.exceptions.GerenciamentoException;
import com.sessao.votacao.gerenciamentovotacao.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(GerenciamentoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleGerenciamentoException(GerenciamentoException ex){
        String msgError = ex.getMessage();
        return new ApiErrors(msgError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_GATEWAY)
    public Map<Path, String> handleConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

        Map<Path, String> fieldException = new HashMap<>();
        constraintViolations.stream().forEach(e -> {
            fieldException.put(e.getPropertyPath(), e.getMessage());
        });
        return fieldException;
    }

}
