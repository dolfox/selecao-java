package com.indra.desafio.controller.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.indra.desafio.domain.model.CombustivelModel;
import com.indra.desafio.domain.service.CombustivelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/combustivel")
@Api(value = "API REST desafioIndra")
public class CombustivelResource {

	@Autowired
	private CombustivelService combustivelService;

	@ApiOperation(value = "Retorna uma lista completa de combustíveis")
	@GetMapping
	public ResponseEntity<List<CombustivelModel>> ListarCombustiveis() {
		return ResponseEntity.status(HttpStatus.OK).body(combustivelService.listarCombustiveis());
	}

	@ApiOperation(value = "Salva informações completas de combustível")
	@PostMapping
	public ResponseEntity<CombustivelModel> salvarCombustivel(@RequestBody CombustivelModel combustivel) {
		combustivel = combustivelService.salvarCombustivel(combustivel);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(combustivel.getId())
				.toUri();

		return ResponseEntity.created(uri).body(combustivel);
	}

	@ApiOperation(value = "Returna média de preço por município")
	@GetMapping("/media-de-preco/{municipio}")
	public ResponseEntity<Float> mediaDePrecoPorMunicipio(@PathVariable String municipio) {
		return new ResponseEntity<Float>(this.combustivelService.mediaDePrecoPorMunicipio(municipio), HttpStatus.OK);
	}

	@ApiOperation(value = "Returna dados por região")
	@GetMapping("/dados-por-sigla-regiao/{siglaRegiao}")
	public ResponseEntity<List<CombustivelModel>> listartDadosPorsiglaRegiao(@PathVariable String siglaRegiao) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(combustivelService.listarMunicipiospPorSiglaRegiao(siglaRegiao));
	}

	@ApiOperation(value = "Returna dados por município")
	@GetMapping("/dados-por-sigla-estado/{siglaEstado}")
	public ResponseEntity<List<CombustivelModel>> listartDadosPorSiglaEstado(@PathVariable String siglaEstado) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(combustivelService.listarMunicipiospPorSiglaEstado(siglaEstado));
	}

	@ApiOperation(value = "Retorna todas as informações por data de coleta")
	@GetMapping("/dados-por-data-coleta")
	public ResponseEntity<List<CombustivelModel>> dadosAgrupadosPorDataColeta() {
		return ResponseEntity.status(HttpStatus.OK).body(combustivelService.getDadosAgrupadosPorDataColeta());
	}

}
