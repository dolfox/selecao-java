package com.indra.desafio.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indra.desafio.domain.exceptions.NegocioNaoEncontradoException;
import com.indra.desafio.domain.model.HistoricoDePrecoDeCombustivel;
import com.indra.desafio.domain.repository.HistoricoDePrecoDeCombustivelRepository;

@Service
public class HistoricoDePrecoDeCombustivelService {

	@Autowired
	private HistoricoDePrecoDeCombustivelRepository historicoRepository;
	
	public List<HistoricoDePrecoDeCombustivel> listarHistorico(){
		return historicoRepository.findAll();
	}
	
	public HistoricoDePrecoDeCombustivel salvarHistorico(HistoricoDePrecoDeCombustivel
			historico) {
		historico.setId(null);
		historico.setData(OffsetDateTime.now());
		return historicoRepository.save(historico);
	}
	
	public HistoricoDePrecoDeCombustivel buscarHistorico(Long id) {
		
		HistoricoDePrecoDeCombustivel historico = historicoRepository.findById(id)
				.orElseThrow(() -> new NegocioNaoEncontradoException("Histórico não encontrado!"));
		
		return historico;
	}
	
	public void deletarHistorico(Long id) {
		existeHistorico(id);
		
		historicoRepository.deleteById(id);
	}
	
	public void atualizarHistorico(Long id, HistoricoDePrecoDeCombustivel historico) {
		HistoricoDePrecoDeCombustivel historicoSalvo = historicoRepository.findById(id)
				.orElseThrow(()-> new NegocioNaoEncontradoException("Histórico não encontrado!"));
		
		historico.setId(id);
		BeanUtils.copyProperties(historico, historicoSalvo, "data");
		historicoRepository.save(historicoSalvo);
	}
	
	private void existeHistorico(Long id) {
		if(!historicoRepository.existsById(id)) {
			throw new NegocioNaoEncontradoException("Histórico não encontrado!");
		}
	}
}
