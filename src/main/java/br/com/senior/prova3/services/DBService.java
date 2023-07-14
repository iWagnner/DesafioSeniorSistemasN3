package br.com.senior.prova3.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senior.prova3.domain.Pedido;
import br.com.senior.prova3.domain.PedidoItem;
import br.com.senior.prova3.domain.Pessoa;
import br.com.senior.prova3.domain.Produto;
import br.com.senior.prova3.domain.Usuario;
import br.com.senior.prova3.domain.enums.EnumAtivoInativo;
import br.com.senior.prova3.domain.enums.EnumEstadoIBGE;
import br.com.senior.prova3.domain.enums.EnumPerfil;
import br.com.senior.prova3.domain.enums.EnumProdutoServico;
import br.com.senior.prova3.domain.enums.EnumSexo;
import br.com.senior.prova3.domain.enums.EnumSimNao;
import br.com.senior.prova3.domain.enums.EnumSituacaoPedido;
import br.com.senior.prova3.domain.enums.EnumTipoPerfilUsuario;
import br.com.senior.prova3.domain.enums.EnumTipoPessoa;
import br.com.senior.prova3.repositories.impl.PedidoItemRepositoryImpl;
import br.com.senior.prova3.repositories.impl.PedidoRepositoryImpl;
import br.com.senior.prova3.repositories.impl.PessoaRepositoryImpl;
import br.com.senior.prova3.repositories.impl.ProdutoRepositoryImpl;
import br.com.senior.prova3.repositories.impl.UsuarioRepositoryImpl;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
@Service
public class DBService {

	@Autowired
	private UsuarioRepositoryImpl usuarioRepository;
	@Autowired
	private PessoaRepositoryImpl pessoaRepository;
	@Autowired
	private ProdutoRepositoryImpl produtoRepository;
	@Autowired
	private PedidoItemRepositoryImpl pedidoItemRepository;
	@Autowired
	private PedidoRepositoryImpl pedidoRepository;

	public void iniciaDB() {
		//Carga base para testes 
		LocalDate dataNascimento = LocalDate.of(1995, 11, 8);
		//Usuarios
		Usuario usu1 = new Usuario(null, "Usuario 1", "47-99756-3062", "Usu01@gmail.com", "Rua Sao Paulo", "Senior",
				"825", EnumAtivoInativo.ATIVO, EnumPerfil.USUARIO, "Usu01", "Senha01",
				EnumTipoPerfilUsuario.ADMINISTRADOR, EnumSimNao.SIM, "10676004938", dataNascimento, EnumEstadoIBGE.ACRE,
				EnumSexo.MASCULINO, EnumTipoPessoa.FISICA);

		Usuario usu2 = new Usuario(null, "Usuario 2", "47-98440-3366", "Usu02@gmail.com", "Rua Sao Paulo", "Senior",
				"825", EnumAtivoInativo.ATIVO, EnumPerfil.USUARIO, "Usu02", "Senha02", EnumTipoPerfilUsuario.VENDEDOR,
				EnumSimNao.SIM, "521.537.540-20", dataNascimento, EnumEstadoIBGE.MATO_GROSSO_DO_SUL, EnumSexo.FEMININO,
				EnumTipoPessoa.FISICA);
		

		Usuario usu3 = new Usuario(null, "Usuario 3", "47-98440-3366", "Usu03@gmail.com", "Rua Sao Paulo", "Senior",
				"825", EnumAtivoInativo.ATIVO, EnumPerfil.USUARIO, "Usu03", "Senha03", EnumTipoPerfilUsuario.ANALISTACREDITO,
				EnumSimNao.SIM, "293.246.010-24", dataNascimento, EnumEstadoIBGE.PARA, EnumSexo.MASCULINO,
				EnumTipoPessoa.FISICA);
		

		Usuario usu4 = new Usuario(null, "Usuario 4", "47-98440-3366", "Usu04@gmail.com", "Rua Sao Paulo", "Senior",
				"825", EnumAtivoInativo.ATIVO, EnumPerfil.USUARIO, "Usu04", "Senha04", EnumTipoPerfilUsuario.FINANCEIRO,
				EnumSimNao.SIM, "404.963.380-99", dataNascimento, EnumEstadoIBGE.AMAPA, EnumSexo.FEMININO,
				EnumTipoPessoa.FISICA);

		usuarioRepository.saveAll(Arrays.asList(usu1, usu2, usu3, usu4));

		//Pessoas Fisicas e juridicas
		Pessoa pessoa01 = new Pessoa(null, "Cliente01", "4799988-7766", "Cli01@gmail.com", "Rua 7 Setembro", "Neumarket",
				"1276", EnumAtivoInativo.ATIVO, EnumSimNao.SIM, EnumTipoPessoa.FISICA, null, "953.054.690-40", null,
				dataNascimento, EnumSexo.MASCULINO, EnumEstadoIBGE.SANTA_CATARINA, EnumPerfil.CLIENTE);
		

		Pessoa pessoa02 = new Pessoa(null, "Fornecedor01", "473322-1100", "For01@gmail.com", "Rua das missões", "Loja Esquina",
				"5522", EnumAtivoInativo.ATIVO, EnumSimNao.SIM, EnumTipoPessoa.JURIDICA, "Razão social fornecedor 01", "73.402.988/0001-99", "450.841.090",
				null, null, null, EnumPerfil.FORNECEDOR);
		
		pessoaRepository.saveAll(Arrays.asList(pessoa01, pessoa02));
		
		//Produtos
		Produto produto01 = new Produto(null, 1000L, pessoa02, "TV 55 LG", EnumProdutoServico.PRODUTO, EnumAtivoInativo.ATIVO, BigDecimal.valueOf(1000), "UN", null, EnumSimNao.SIM, BigDecimal.valueOf(50), null, BigDecimal.valueOf(50), EnumSimNao.NAO, null);
		
		Produto produto02 = new Produto(null, 1002L, pessoa02, "Radio", EnumProdutoServico.PRODUTO, EnumAtivoInativo.ATIVO, BigDecimal.valueOf(500), "UN", null, EnumSimNao.SIM, BigDecimal.valueOf(10), null, BigDecimal.valueOf(10), EnumSimNao.NAO, null);
		
		Produto servico01 = new Produto(null, 2000L, pessoa02, "Entrega Outro Estado", EnumProdutoServico.SERVICO, EnumAtivoInativo.ATIVO, BigDecimal.valueOf(199.99), null, null, EnumSimNao.SIM, BigDecimal.valueOf(100), null, BigDecimal.valueOf(100), EnumSimNao.SIM, null);
		
		produtoRepository.saveAll(Arrays.asList(produto01, produto02, servico01));
	
		//Pedidos
		Pedido pedido01 = new Pedido(null, pessoa01, usu2, null, EnumSituacaoPedido.ABERTO, BigDecimal.valueOf(199.99), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(199.99), BigDecimal.valueOf(199.99), BigDecimal.ZERO, null);
		
		Pedido pedido02 = new Pedido(null, pessoa01, usu3, null, EnumSituacaoPedido.ABERTO, BigDecimal.valueOf(1500), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(1500), BigDecimal.valueOf(1500), BigDecimal.ZERO, null);

		Pedido pedido03 = new Pedido(null, pessoa01, usu2, null, EnumSituacaoPedido.ABERTO, BigDecimal.valueOf(500), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(500), BigDecimal.valueOf(500), BigDecimal.ZERO, null);

		pedidoRepository.saveAll(Arrays.asList(pedido01, pedido02, pedido03));
		
		//Items Pedidos				
		PedidoItem pedido1Item1 = new PedidoItem(null, pedido01, servico01, 1L, servico01.getCodigo(), servico01.getNome(), BigDecimal.ONE, servico01.getPreco(), servico01.getPreco(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, servico01.getSiglaUnidadeMedida(), EnumSimNao.NAO, EnumProdutoServico.SERVICO);
		
		PedidoItem pedido2Item1 = new PedidoItem(null, pedido02, produto01, 1L, produto01.getCodigo(), produto01.getNome(), BigDecimal.ONE, produto01.getPreco(), produto01.getPreco(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, produto01.getSiglaUnidadeMedida(), EnumSimNao.NAO, EnumProdutoServico.PRODUTO);
		PedidoItem pedido2Item2 = new PedidoItem(null, pedido02, produto02, 2L, produto02.getCodigo(), produto02.getNome(), BigDecimal.ONE, produto02.getPreco(), produto02.getPreco(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, produto02.getSiglaUnidadeMedida(), EnumSimNao.NAO, EnumProdutoServico.PRODUTO);

		
		pedidoItemRepository.saveAll(Arrays.asList(pedido1Item1, pedido2Item1, pedido2Item2));
		
	}
}
