package br.com.senior.prova3.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senior.prova3.domain.Pedido;
import br.com.senior.prova3.domain.PedidoItem;
import br.com.senior.prova3.domain.Pessoa;
import br.com.senior.prova3.domain.Usuario;
import br.com.senior.prova3.domain.dto.PedidoDTO;
import br.com.senior.prova3.domain.dto.PedidoItemDTO;
import br.com.senior.prova3.domain.enums.EnumPerfil;
import br.com.senior.prova3.domain.enums.EnumProdutoServico;
import br.com.senior.prova3.domain.enums.EnumSituacaoPedido;
import br.com.senior.prova3.repositories.impl.PedidoItemRepositoryImpl;
import br.com.senior.prova3.repositories.impl.PedidoRepositoryImpl;
import br.com.senior.prova3.repositories.impl.PessoaRepositoryImpl;
import br.com.senior.prova3.repositories.impl.UsuarioRepositoryImpl;
import br.com.senior.prova3.services.exceptions.DataIntegrityViolationException;
import br.com.senior.prova3.services.exceptions.IllegalArgumentException;
import br.com.senior.prova3.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */

@Service
public class PedidoService {

	@Autowired
	private PedidoRepositoryImpl repository;
	@Autowired
	private PessoaRepositoryImpl pessoaRepositoryImpl;
	@Autowired
	private UsuarioRepositoryImpl usuarioRepositoryImpl;
	@Autowired
	private PedidoItemRepositoryImpl pedidoItemRepositoryImpl;
	@Autowired
	private PedidoItemService pedidoItemService;


	public Page<Pedido> findAllOnFilter(Pageable pageable, UUID idcliente, UUID idUsuarioGerador, LocalDate dataCriacao) {
		Page<Pedido> pedido;
		if (idcliente == null && idUsuarioGerador == null && dataCriacao == null) {
			pedido = repository.findAll(pageable);
		} else {
			pedido = repository.findAllOnFilter(pageable, idcliente, idUsuarioGerador, dataCriacao);
		}
		if (pedido.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum cadastro foi encontrado");
		}
		return pedido;
	}
	public Pedido findById(UUID id) {
		Optional<Pedido> pedido = repository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrada: " + id));
	}
	@Transactional
	public Pedido create(@Valid PedidoDTO pedidoDTO) throws DataIntegrityViolationException {
		Pedido pedido = repository.save(newPedido(pedidoDTO));
		Pedido pedidoAposInserirItens = pedidoItemService.create(pedido, pedidoDTO);
		PedidoDTO newPedidoDTO = new PedidoDTO(pedidoAposInserirItens);
		return update(newPedidoDTO.getId(), newPedidoDTO);
	}
	
	public Pedido create(UUID id, @Valid PedidoDTO pedidoDTO) throws DataIntegrityViolationException {
		pedidoDTO.setId(id);	
		Pedido oldPedido = findById(id);
		oldPedido = repository.save(newPedido(pedidoDTO));
		Pedido pedidoAposInserirItens = pedidoItemService.create(oldPedido, pedidoDTO);
		PedidoDTO newPedidoDTO = new PedidoDTO(pedidoAposInserirItens);
		return update(newPedidoDTO.getId(), newPedidoDTO);
	}
	
	public Pedido update(UUID id, @Valid PedidoDTO pedidoDTO) {
		pedidoDTO.setId(id);	
		Pedido oldPedido = findById(id);
		oldPedido = newPedido(pedidoDTO);
		return repository.save(oldPedido);
	}
	
	public void delete(UUID id) {
		Pedido pedido = findById(id);
		if(pedido.getSituacao() == EnumSituacaoPedido.ABERTO) {
			pedido.setSituacao(EnumSituacaoPedido.CANCELADO);
			repository.save(pedido);
		}else {
			throw new DataIntegrityViolationException("Não é possível cancelar esse pedido, pois o mesmo não se encontra mais como a situação de aberto.");
		}
	}

	public Pedido newPedido(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();
		
		if (pedidoDTO.getId() != null) {
			Pedido pedidoExiste = this.findById(pedidoDTO.getId());
			if (pedidoExiste != null) {
				pedido.setId(pedidoExiste.getId());
			}
		}
		
		Pessoa cliente = buscarCliente(pedidoDTO.getIdCliente());
		Usuario usuario = buscarUsuarioGerador(pedidoDTO.getIdUsuarioGerador());

		pedido.setCliente(cliente);
		pedido.setUsuarioGerador(usuario); 
		pedido.setSituacao(pedidoDTO.getSituacao()); 
		pedido.setObservacoes(null);
		pedido.setTotalProdutos(calculaTotalProdutos(pedidoDTO));
		pedido.setTotalServicos(calculaTotalServicos(pedidoDTO));
		pedido.setTotalAcrescimo(calculaTotalAcrescimo(pedidoDTO));
		pedido.setTotalDesconto(calculaTotalDesconto(pedidoDTO));
		pedido.setTotalBruto(calculaTotalBruto(pedidoDTO));
		pedido.setTotalLiquido(calculaTotalLiquido(pedidoDTO));
		pedido.setPercentualDesconto(pedidoDTO.getPercentualDesconto());
		pedido.setCurrenttimemillis(System.currentTimeMillis());
		
		return pedido;
	}
	
	public Pessoa buscarCliente(UUID id) {
		 Optional<Pessoa> optionalPessoa = pessoaRepositoryImpl.findById(id);
	    if (optionalPessoa.isPresent()) {
	        Pessoa pessoa = optionalPessoa.get();
	        if (pessoa.getPerfis().contains(EnumPerfil.CLIENTE)) {
	            return pessoa;
	        } else {
	            throw new IllegalArgumentException("ID informado não pertence a um Cliente.");
	        }
	    } else {
	        throw new IllegalArgumentException("Cliente não encontrado com o ID informado.");
	    }
	}
	
	public Usuario buscarUsuarioGerador(UUID id) {
		 Optional<Usuario> optionalUsuario = usuarioRepositoryImpl.findById(id);
	    if (optionalUsuario.isPresent()) {
	        Usuario usuario = optionalUsuario.get();
	        if (usuario.getPerfis().contains(EnumPerfil.USUARIO)) {
	            return usuario;
	        } else {
	            throw new IllegalArgumentException("ID informado não pertence a um Usuario.");
	        }
	    } else {
	        throw new IllegalArgumentException("Usuario não encontrado com o ID informado.");
	    }
	}
	
	public Page<Pedido> findAllPedidosByIdProduto(Pageable pageable, UUID id) {
		Page<Pedido> pedidos = repository.findAllPedidosByIdProduto(pageable, id);
		if (pedidos.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum Pedido foi encontrado para esse produto");
		}
		return pedidos;
	}

	public List<PedidoItem> carregaPedidoItem(UUID id) {
		List<PedidoItem> pedidoItens = new ArrayList<>();
		if(id != null) {
		 pedidoItens = pedidoItemRepositoryImpl.findAllPedidosItemByIdPedido(id);
		return pedidoItens;
		}
		return null;
	}
	
	public boolean existeItemTipoProduto(UUID idpedido, EnumProdutoServico produtoServico) {
		List<PedidoItem> pedidoItens = carregaPedidoItem(idpedido);
		if (pedidoItens != null) {
			for (PedidoItem p : pedidoItens) {
				if (produtoServico == p.getProduto().getTipo()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public BigDecimal calculaTotalProdutos(PedidoDTO pedidoDTO) {
		BigDecimal totalProdutos = BigDecimal.ZERO;
		for(PedidoItemDTO p : pedidoDTO.getPedidoitemDTO()) {
			if(EnumProdutoServico.PRODUTO == p.getTipo()) {
				totalProdutos = totalProdutos.add(p.getTotalBruto());				
			}
		}
		return totalProdutos;
	}

	public BigDecimal calculaTotalServicos(PedidoDTO pedidoDTO) {
		BigDecimal totalServicos = BigDecimal.ZERO;
		for(PedidoItemDTO p : pedidoDTO.getPedidoitemDTO()) {
			if(EnumProdutoServico.SERVICO == p.getTipo()) {
				totalServicos = totalServicos.add(p.getTotalBruto());
			}
		}

		return totalServicos;
	}

	public BigDecimal calculaTotalDesconto(PedidoDTO pedidoDTO) {
		BigDecimal totalDesconto = BigDecimal.ZERO;
		for(PedidoItemDTO p : pedidoDTO.getPedidoitemDTO()) {
			totalDesconto = totalDesconto.add(p.getTotalDesconto());
		}
		return totalDesconto;
	}

	public BigDecimal calculaTotalAcrescimo(PedidoDTO pedidoDTO) {
		BigDecimal totalAcrescimo = BigDecimal.ZERO;
		for(PedidoItemDTO p : pedidoDTO.getPedidoitemDTO()) {
			totalAcrescimo = totalAcrescimo.add(p.getTotalAcrescimo());
		}
		return totalAcrescimo;
	}
	
	public BigDecimal calculaTotalBruto(PedidoDTO pedidoDTO) {
		BigDecimal totalBruto = calculaTotalProdutos(pedidoDTO).add(calculaTotalServicos(pedidoDTO));
		return totalBruto;
	}
	
	public BigDecimal calculaTotalLiquido(PedidoDTO pedidoDTO) {
		BigDecimal totalLiquido = calculaTotalBruto(pedidoDTO).add(calculaTotalAcrescimo(pedidoDTO)).subtract(calculaTotalDesconto(pedidoDTO));
		return totalLiquido;
	}
}
