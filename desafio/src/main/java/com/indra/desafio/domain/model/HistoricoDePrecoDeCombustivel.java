package com.indra.desafio.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "historico_de_preco_combustivel")
public class HistoricoDePrecoDeCombustivel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 30)
	private String tipoCombustivel;

	@NotBlank
	@Size(max = 100)
	private String posto;

	@NotNull
	private Double preco;

	@JsonProperty(access = Access.READ_ONLY)
	@JsonFormat(pattern="dd-MM-yyyy'T'HH:mm")
	private OffsetDateTime data;

	

}
