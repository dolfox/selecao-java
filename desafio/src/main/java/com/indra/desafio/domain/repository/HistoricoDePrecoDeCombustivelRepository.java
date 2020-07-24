package com.indra.desafio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indra.desafio.domain.model.HistoricoDePrecoDeCombustivel;

@Repository
public interface HistoricoDePrecoDeCombustivelRepository extends JpaRepository<HistoricoDePrecoDeCombustivel, Long> {

}
