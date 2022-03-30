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
import com.pucminas.tcc.domain.ItemPedido;
import com.pucminas.tcc.domain.Pagamento;
import com.pucminas.tcc.domain.PagamentoBoleto;
import com.pucminas.tcc.domain.PagamentoCartao;
import com.pucminas.tcc.domain.Pedido;
import com.pucminas.tcc.domain.Produto;
import com.pucminas.tcc.domain.enums.EstadoPagamento;
import com.pucminas.tcc.domain.enums.TipoCliente;
import com.pucminas.tcc.repositories.CategoriaRepository;
import com.pucminas.tcc.repositories.CidadeRepository;
import com.pucminas.tcc.repositories.ClienteRepository;
import com.pucminas.tcc.repositories.EnderecoRepository;
import com.pucminas.tcc.repositories.EstadoRepository;
import com.pucminas.tcc.repositories.ItemPedidoRepository;
import com.pucminas.tcc.repositories.PagamentoRepository;
import com.pucminas.tcc.repositories.PedidoRepository;
import com.pucminas.tcc.repositories.ProdutoRepository;

@SpringBootApplication
public class AppstoreApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
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
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	
	public static void main(String[] args) {
		SpringApplication.run(AppstoreApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática e Acessorios");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, Mesa e Banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 7000.00);
		Produto p2 = new Produto(null, "Impressora", 850.00);
		Produto p3 = new Produto(null, "Mouse", 150.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		
		Estado est1 = new Estado(null, "Distrito Federal");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Brasília", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		
		Cliente cli1 = new Cliente(null, "Miguel Siqueira", "miguel@mail.com", "12069814076", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("61999887755", "61999887757"));
		
		Endereco e1 = new Endereco(null, "Quadra Externa", "1", "Casa 03", "Guará", "70000-100", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Paulista", "1000", "Edifício", "Centro", "70000-200", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("27/03/2022 13:00"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("27/03/2022 15:00"), cli1, e2);
		
		Pagamento pgto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("27/03/2021 15:58"), null);
		ped2.setPagamento(pgto2);		

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 7000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 150.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 850.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}

}
