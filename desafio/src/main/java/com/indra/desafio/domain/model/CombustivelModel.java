package com.indra.desafio.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "combustivel")
public class CombustivelModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String siglaRegiao;
	private String siglaEstado;
	private String municipio;	
	private String revenda;
	private String cnpjRevenda;
	private String produto;
	private OffsetDateTime dataColeta;
	private BigDecimal valorVenda;
	private BigDecimal valorCompra;
	private Integer unidadeMedida;
	private String bandeira;
}
