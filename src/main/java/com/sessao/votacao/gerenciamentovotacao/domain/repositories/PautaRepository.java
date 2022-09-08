package com.sessao.votacao.gerenciamentovotacao.domain.repositories;

import com.sessao.votacao.gerenciamentovotacao.domain.entities.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, Integer> {
}
