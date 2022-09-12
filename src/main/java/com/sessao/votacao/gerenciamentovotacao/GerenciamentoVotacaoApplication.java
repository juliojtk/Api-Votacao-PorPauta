package com.sessao.votacao.gerenciamentovotacao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class GerenciamentoVotacaoApplication {
	private static final Logger log = LoggerFactory.getLogger(GerenciamentoVotacaoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(GerenciamentoVotacaoApplication.class, args);
		log.info("API iniciada com sucesso!");
	}

}
