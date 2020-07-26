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

import com.indra.desafio.domain.model.UsuarioModel;
import com.indra.desafio.domain.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
@Api(value="API REST desafioIndra")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@ApiOperation(value="Retorna uma lista de Usuários")
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> listarUsuarios(){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService
				.listarUsuarios());
	}
	
	@ApiOperation(value="Salva um Usuário")
	@PostMapping
	public ResponseEntity<UsuarioModel> cadastrarUsuario(@Valid @RequestBody UsuarioModel usuario){
		
		usuario = usuarioService.cadastrarUsuario(usuario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(usuario);
	}
	
	@ApiOperation(value="Retorna um Usuário passando um id como parâmetro")
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> buscarUsuario(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarUsuario(id));
	}
	
	@ApiOperation(value="Deleta um Usuário passando id como parâmetro")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
		usuarioService.deletarUsuario(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@ApiOperation(value="Atualiza um Usuário")
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioModel> atualizarUsuario(@PathVariable Long id, 
			@Valid @RequestBody UsuarioModel usuario){
		usuario = usuarioService.atualizarUsuario(id, usuario);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
