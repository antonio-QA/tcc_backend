package com.pucminas.tcc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pucminas.tcc.domain.Categoria;
import com.pucminas.tcc.domain.Cidade;
import com.pucminas.tcc.domain.Cliente;
import com.pucminas.tcc.domain.Endereco;
import com.pucminas.tcc.domain.Estado;
import com.pucminas.tcc.domain.Pagamento;
import com.pucminas.tcc.domain.PagamentoBoleto;
import com.pucminas.tcc.domain.PagamentoCartao;
import com.pucminas.tcc.domain.Pedido;
import com.pucminas.tcc.domain.Produto;
import com.pucminas.tcc.domain.enuns.EstadoPagamento;
import com.pucminas.tcc.domain.enuns.TipoCliente;
import com.pucminas.tcc.repositories.CategoriaRepository;
import com.pucminas.tcc.repositories.CidadeRepository;
import com.pucminas.tcc.repositories.ClienteRepository;
import com.pucminas.tcc.repositories.EnderecoRepository;
import com.pucminas.tcc.repositories.EstadoRepository;
import com.pucminas.tcc.repositories.PagamentoRepository;
import com.pucminas.tcc.repositories.PedidoRepository;
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
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	
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

		Cliente cli1 = new Cliente(null, "Miguel Siqueira", "miguel@mail.com", "12069814076", TipoCliente.PESSOAFISICA);
		
		Endereco e1 = new Endereco(null, "Quadra Externa", "1", "Casa 03", "Guará", "70000-100", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Paulista", "1000", "Edifício", "Centro", "70000-200", cli1, c2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("27/03/2022 13:00"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("27/03/2022 15:00"), cli1, e2);
		
		Pagamento pgto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pgto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("27/03/2021 15:58"), null);
		

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		cli1.getTelefones().addAll(Arrays.asList("61999887755", "61999887757"));
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		ped1.setPagamento(pgto1);
		ped2.setPagamento(pgto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtorepository.saveAll(Arrays.asList(p1, p2, p3));
		estadorepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
		
	}

}
