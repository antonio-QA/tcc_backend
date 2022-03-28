package com.pucminas.tcc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pucminas.tcc.domain.Categoria;
import com.pucminas.tcc.domain.Cidade;
import com.pucminas.tcc.domain.Estado;
import com.pucminas.tcc.domain.Produto;
import com.pucminas.tcc.repositories.CategoriaRepository;
import com.pucminas.tcc.repositories.CidadeRepository;
import com.pucminas.tcc.repositories.EstadoRepository;
import com.pucminas.tcc.repositories.ProdutoRepository;

@SpringBootApplication
public class AppstoreApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtorepository;
	
	@Autowired
	private EstadoRepository estadorepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	
	public static void main(String[] args) {
		SpringApplication.run(AppstoreApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 7000.00);
		Produto p2 = new Produto(null, "Impressora", 850.00);
		Produto p3 = new Produto(null, "Mouse", 150.00);
		
		Estado est1 = new Estado(null, "Distrito Federal");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Brasília", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtorepository.saveAll(Arrays.asList(p1, p2, p3));
		estadorepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
	}

}
