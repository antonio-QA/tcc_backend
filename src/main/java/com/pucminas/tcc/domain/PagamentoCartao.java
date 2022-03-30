package com.pucminas.tcc.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.pucminas.tcc.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoCartao")
public class PagamentoCartao extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	private Integer numeroParcelas;
	
	
	public PagamentoCartao() {
	}

	public PagamentoCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
		super(id, estado, pedido);
		this.numeroParcelas = numeroParcelas;
	}
	

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

}
