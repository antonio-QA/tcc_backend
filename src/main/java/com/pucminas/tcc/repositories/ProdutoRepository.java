package com.pucminas.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pucminas.tcc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
