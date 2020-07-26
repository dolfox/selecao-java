package com.indra.desafio.controller.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.indra.desafio.domain.model.HistoricoDePrecoDeCombustivel;
import com.indra.desafio.domain.service.HistoricoDePrecoDeCombustivelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/historico-combustivel")
@Api(value="API REST desafioIndra")
public class HistoricoDePrecoDeCombustivelResource {
	
	@Autowired
	private HistoricoDePrecoDeCombustivelService historicoService;
	
	@ApiOperation(value="Retorna uma lista de historico")
	@GetMapping
	public ResponseEntity<List<HistoricoDePrecoDeCombustivel>> listarHistorico(){
		return ResponseEntity.status(HttpStatus.OK).body(historicoService
				.listarHistorico());
	}
	
	@ApiOperation(value="Salva um historico")
	@PostMapping
	public ResponseEntity<HistoricoDePrecoDeCombustivel> salvarHistorico(@Valid 
			@RequestBody HistoricoDePrecoDeCombustivel historico){
		
		historico = historicoService.salvarHistorico(historico);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(historico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(historico);
	}
	
	@ApiOperation(value="Retorna um historico recebendo um id como parâmetro")
	@GetMapping("/{id}")
	public ResponseEntity<HistoricoDePrecoDeCombustivel> buscarHistorico(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(historicoService.buscarHistorico(id));
	}
	
	@ApiOperation(value="Deleta um historico")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarHistorico(@PathVariable Long id){
		historicoService.deletarHistorico(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Atualiza um historico")
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarHistorico(@PathVariable Long id, 
			@Valid @RequestBody HistoricoDePrecoDeCombustivel historico){
		historicoService.atualizarHistorico(id, historico);
		
		return ResponseEntity.noContent().build();
	}

}
