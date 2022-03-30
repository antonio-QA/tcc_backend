package com.pucminas.tcc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucminas.tcc.domain.Pedido;
import com.pucminas.tcc.repositories.PedidoRepository;
import com.pucminas.tcc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Pedido não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())
		);
	}
	
}
