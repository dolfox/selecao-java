package com.indra.desafio.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.indra.desafio.domain.model.CombustivelModel;

@Repository
public interface CombustivelRepository extends JpaRepository<CombustivelModel, Long> {
	
	@Query("select c from CombustivelModel c order by c.dataColeta")
	List<CombustivelModel> getDadosAgrupadosPorDataColeta();

	@Query("select avg(c.valorVenda) from CombustivelModel c where c.municipio = ?1")
	float mediaDePrecoPorMunicipio(String nomeMunicipio);
	
	@Query("select c from CombustivelModel c where c.siglaRegiao = ?1")
	List<CombustivelModel> findBysiglaRegiao(String siglaRegiao);
	
	@Query("select c from CombustivelModel c where c.siglaEstado = ?1")
	List<CombustivelModel> findBysiglaEstado(String siglaEstado);



}
