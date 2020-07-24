package com.indra.desafio.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indra.desafio.domain.exceptions.UsuarioExistenteException;
import com.indra.desafio.domain.exceptions.NegocioNaoEncontradoException;
import com.indra.desafio.domain.model.UsuarioModel;
import com.indra.desafio.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<UsuarioModel> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	public UsuarioModel cadastrarUsuario(UsuarioModel usuario) {

		if (usuarioRepository.existsByEmail(usuario.getEmail())) {
			throw new UsuarioExistenteException("Usuário já cadastrado!");
		}

		usuario.setId(null);
		return usuarioRepository.save(usuario);
	}

	public UsuarioModel buscarUsuario(Long id) {
		UsuarioModel usuario = buscar(id);

		return usuario;
	}

	public void deletarUsuario(Long id) {
		UsuarioModel usuario = buscar(id);
		usuarioRepository.delete(usuario);
	}

	public UsuarioModel atualizarUsuario(Long id, UsuarioModel usuario) {

		if(!usuarioRepository.existsById(id)) {
			throw new NegocioNaoEncontradoException("Usuario não encontrado!");
		}
		
		usuario.setId(id);
		return usuarioRepository.save(usuario);
	}

	private UsuarioModel buscar(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new NegocioNaoEncontradoException("Usuário não encontrado!"));
	}
}
