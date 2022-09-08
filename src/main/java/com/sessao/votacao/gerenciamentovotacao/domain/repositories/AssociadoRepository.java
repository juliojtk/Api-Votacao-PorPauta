package com.sessao.votacao.gerenciamentovotacao.domain.repositories;

import com.sessao.votacao.gerenciamentovotacao.domain.entities.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociadoRepository extends JpaRepository<Associado, Integer> {
}
