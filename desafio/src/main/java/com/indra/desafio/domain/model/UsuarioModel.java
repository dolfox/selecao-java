package com.indra.desafio.domain.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuario")
public class UsuarioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 150)
	private String nome;
	
	@NotBlank
	@Size(max = 150)
	@Email
	private String email;
	
	@NotBlank
	private String login;
	
	@NotBlank
	private String senha;
	
	@Embedded
	private EnderecoModel endereco;

}
