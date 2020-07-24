package com.indra.desafio.domain.model;

import javax.persistence.Embeddable;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@Table(name = "endereco")
public class EnderecoModel {

	private String logradouro;
	private String numero;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	
}
