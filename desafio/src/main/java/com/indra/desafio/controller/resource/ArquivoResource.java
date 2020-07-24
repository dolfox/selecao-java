package com.indra.desafio.controller.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.indra.desafio.domain.service.ArquivoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/arquivos")
@Api(value="API REST desafioIndra")
public class ArquivoResource {
	
	@Autowired
	private ArquivoService arquivoService;
	
	@ApiOperation(value="Salva um arquivo")
	@PostMapping
	public void upload(@RequestParam MultipartFile arquivo) {
		arquivoService.salvarArquivo(arquivo);
	}

}
