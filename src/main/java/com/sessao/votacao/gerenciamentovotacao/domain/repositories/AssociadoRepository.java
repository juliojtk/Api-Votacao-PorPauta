package com.sessao.votacao.gerenciamentovotacao.domain.repositories;

import com.sessao.votacao.gerenciamentovotacao.domain.entities.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssociadoRepository extends JpaRepository<Associado, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT pauta_id, voto, COUNT(voto) AS QtdVotos " +
                    "FROM associado as a " +
                    "WHERE a.pauta_id = :pautaId " +
                    "GROUP BY pauta_id, voto")
    List<String> findResultadoVotos(@Param("pautaId") Integer pautaId);
}
