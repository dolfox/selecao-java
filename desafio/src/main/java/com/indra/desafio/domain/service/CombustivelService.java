package com.indra.desafio.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indra.desafio.domain.model.CombustivelModel;
import com.indra.desafio.domain.repository.CombustivelRepository;

@Service
public class CombustivelService {

	@Autowired
	private CombustivelRepository combustivelRepository;
	
	public List<CombustivelModel> listarCombustiveis(){
		return combustivelRepository.findAll();
	}
	
	public CombustivelModel salvarCombustivel(CombustivelModel combustivel) {
		combustivel.setId(null);
		combustivel.setDataColeta(OffsetDateTime.now());
		return combustivelRepository.save(combustivel);
	}
	
	public float mediaDePrecoPorMunicipio(String municipio) {
		return combustivelRepository.mediaDePrecoPorMunicipio(municipio);		
	}
	
	public List<CombustivelModel> listarMunicipiospPorSiglaRegiao(String siglaRegiao){
		return combustivelRepository.findBysiglaRegiao(siglaRegiao);
	}
	
	public List<CombustivelModel> listarMunicipiospPorSiglaEstado(String siglaEstado){
		return combustivelRepository.findBysiglaEstado(siglaEstado);
	}
	
	public List<CombustivelModel> getDadosAgrupadosPorDataColeta() {
		return combustivelRepository.getDadosAgrupadosPorDataColeta();
	}


}
