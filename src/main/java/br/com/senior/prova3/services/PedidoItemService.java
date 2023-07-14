package br.com.senior.prova3.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senior.prova3.domain.Pedido;
import br.com.senior.prova3.domain.PedidoItem;
import br.com.senior.prova3.domain.Produto;
import br.com.senior.prova3.domain.dto.PedidoDTO;
import br.com.senior.prova3.domain.dto.PedidoItemDTO;
import br.com.senior.prova3.domain.enums.EnumAtivoInativo;
import br.com.senior.prova3.domain.enums.EnumProdutoServico;
import br.com.senior.prova3.domain.enums.EnumSimNao;
import br.com.senior.prova3.domain.enums.EnumSituacaoPedido;
import br.com.senior.prova3.repositories.impl.PedidoItemRepositoryImpl;
import br.com.senior.prova3.repositories.impl.PedidoRepositoryImpl;
import br.com.senior.prova3.repositories.impl.ProdutoRepositoryImpl;
import br.com.senior.prova3.services.exceptions.DataIntegrityViolationException;
import br.com.senior.prova3.services.exceptions.IllegalArgumentException;
import br.com.senior.prova3.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
@Service
public class PedidoItemService {
	private static final Logger logger = LoggerFactory.getLogger(PedidoItemService.class);

	@Autowired
	private PedidoItemRepositoryImpl repository;
	@Autowired
	private PedidoRepositoryImpl PedidoRepositoryImpl;
	@Autowired
	private ProdutoRepositoryImpl produtoRepositoryImpl;	

	public Page<PedidoItem> findAllOnFilter(Pageable pageable, UUID idPedido, UUID idProduto, LocalDate dataCriacao) {
		Page<PedidoItem> pedidoItem;
		if (idPedido == null && idProduto == null && dataCriacao == null) {
			pedidoItem = repository.findAll(pageable);
		} else {
			pedidoItem = repository.findAllOnFilter(pageable, idPedido, idProduto, dataCriacao);
		}
		if (pedidoItem.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum cadastro foi encontrado");
		}
		return pedidoItem;
	}
	
	public PedidoItem findById(UUID id) {
		Optional<PedidoItem> pedidoItem = repository.findById(id);
		return pedidoItem.orElseThrow(() -> new ObjectNotFoundException("PedidoItem não encontrado: " + id));
	}

	public Pedido create(Pedido pedido, PedidoDTO pedidoDTO) {
		List<PedidoItem> itens = new ArrayList<>();
		Pedido newPedido = pedido;
		if(pedidoDTO.getPedidoitemDTO() != null) {
			for(PedidoItemDTO i : pedidoDTO.getPedidoitemDTO()) {
				PedidoItemDTO newPedidoItemDTO = i;
				if (pedidoDTO.getPercentualDesconto().compareTo(BigDecimal.ZERO) > 0) {
					if (EnumSituacaoPedido.ABERTO == pedidoDTO.getSituacao() && existeItemTipo(EnumProdutoServico.PRODUTO, pedidoDTO)) {
						 newPedidoItemDTO = calculaPercentualDesconto(pedidoDTO.getPercentualDesconto(), i);
					} else {
						throw new IllegalArgumentException("Percentual de Desconto não permitido. O Pedido deve estar aberto e conter pelo menos um item do tipo Produto.");					}
				}
				
				PedidoItem pedidoItem = repository.save(newPedidoItem(pedido.getId() , newPedidoItemDTO));
				itens.add(pedidoItem);
			}
		}
		pedido.setPedidoitem(itens);
		return newPedido;
	}
	
	
	public PedidoItem update(UUID id, UUID idpedido, @Valid PedidoItemDTO pedidoItemDTO) {
		pedidoItemDTO.setId(id);
		PedidoItem oldPedidoItem = findById(id);
		oldPedidoItem = newPedidoItem(idpedido, pedidoItemDTO);
		return repository.save(oldPedidoItem);
	}
	
	public void delete(UUID id) {
		PedidoItem pedidoItem = findById(id);
		Pedido pedido = carregaPedido(pedidoItem.getPedido().getId());
		if(pedido.getSituacao() == EnumSituacaoPedido.ABERTO) {
			pedidoItem.setCancelado(EnumSimNao.SIM);
			pedidoItem.setDataCancelamento(LocalDate.now());
			repository.save(pedidoItem);
		}else {
			throw new DataIntegrityViolationException("Não é possível excluir esse item pois o pedido não está aberto. Situação do pedido atual: " + pedido.getSituacao());
		}
	}

	public PedidoItem newPedidoItem(UUID idPedido, PedidoItemDTO pedidoItemDTO) {
		PedidoItem pedidoItem = new PedidoItem();
		logger.info(pedidoItemDTO.getIdproduto().toString());
		
		Produto produto = carregaProduto(pedidoItemDTO.getIdproduto());
		
		if(produto == null) {
			throw new DataIntegrityViolationException("Produto vinculado a esse item não existe.");
		}
		
		if(EnumAtivoInativo.INATIVO == produto.getSituacao()) {
			 throw new IllegalArgumentException("Não é possivel adicionar esse item. Motivo: Produto INATIVO");
		}
		
		Pedido pedido = carregaPedido(idPedido);
		if(pedido == null) {
			throw new DataIntegrityViolationException("Pedido vinculado a esse item não existe, favor verificar a integridade das informações.");
		}
		
		if (pedidoItemDTO.getId() != null) {
			PedidoItem pedidoItemExiste = this.findById(pedidoItemDTO.getId());
			if (pedidoItemExiste != null && pedidoItemExiste.getCancelado() == EnumSimNao.NAO) {
				pedidoItem.setId(pedidoItemExiste.getId()); 
				pedidoItem.setDataInclusao(pedidoItemExiste.getDataInclusao()); 
			}else {
				throw new IllegalArgumentException("Alteração não permitida. Motivo: Item Cancelado.");
			}
		}
		
		
		pedidoItem.setPedido(pedido); 
		pedidoItem.setProduto(produto);
		pedidoItem.setTipo(produto.getTipo());
		pedidoItem.setSequencial(pedidoItemDTO.getSequencial()); 
		pedidoItem.setCodigoProduto(produto.getCodigo());
		pedidoItem.setNomeProduto(produto.getNome());
		pedidoItem.setQuantidade(pedidoItemDTO.getQuantidade()); 
		pedidoItem.setTotalBruto(calculaTotalBruto(pedidoItemDTO));
		pedidoItem.setTotalLiquido(calculaTotalLiquido(pedidoItemDTO));
		pedidoItem.setTotalDesconto(calculaTotalDesconto(pedidoItemDTO)); 
		pedidoItem.setTotalAcrescimo(calculaTotalAcrescimo(pedidoItemDTO));
		pedidoItem.setDescontoRateado(pedidoItemDTO.getDescontoRateado());
		pedidoItem.setDesconto(pedidoItemDTO.getDesconto());
		pedidoItem.setAcrecimo(pedidoItemDTO.getAcrecimo());
		pedidoItem.setSiglaUnidadeMedida(produto.getSiglaUnidadeMedida());
		pedidoItem.setCurrenttimemillis(System.currentTimeMillis());
		
		return pedidoItem;
	}
	
	public Page<PedidoItem> findAllPedidosItemIdProduto(Pageable pageable, UUID id) {
		Page<PedidoItem> pedidosItens = repository.findAllPedidosItemByIdProduto(pageable, id);
		if (pedidosItens.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum PedidoItem foi encontrado para esse produto");
		}
		return pedidosItens;
	}
	
	public Pedido carregaPedido(UUID id) {
		Pedido pedido = PedidoRepositoryImpl.findByIdMandatory(id);
		return pedido;
	}
	
	public Produto carregaProduto(UUID id) {
		Produto produto = produtoRepositoryImpl.findByIdMandatory(id);
		return produto;
	}
	
	public BigDecimal calculaTotalBruto(PedidoItemDTO pedidoItemDTO) {
		Produto produto = carregaProduto(pedidoItemDTO.getIdproduto());
		BigDecimal total = pedidoItemDTO.getQuantidade().multiply(produto.getPreco());
		return total;
	}
	
	public BigDecimal calculaTotalDesconto(PedidoItemDTO pedidoItemDTO) {
		BigDecimal totalDesconto = pedidoItemDTO.getDesconto().add(pedidoItemDTO.getDescontoRateado());
		return totalDesconto;
	}
	
	public PedidoItemDTO calculaPercentualDesconto(BigDecimal percentualDesconto, PedidoItemDTO pedidoItemDTO) {
		Produto produto = carregaProduto(pedidoItemDTO.getIdproduto());
		BigDecimal totalBruto = pedidoItemDTO.getQuantidade().multiply(produto.getPreco());
		BigDecimal decimalDesconto = percentualDesconto.divide(BigDecimal.valueOf(100));
		BigDecimal valorDescontoItem = totalBruto.multiply(decimalDesconto);
		pedidoItemDTO.setDescontoRateado(valorDescontoItem);
		return pedidoItemDTO;
	}
	public BigDecimal calculaTotalAcrescimo(PedidoItemDTO pedidoItemDTO) {
		BigDecimal totalAcrescimo = pedidoItemDTO.getAcrecimo();
		return totalAcrescimo;
	}
	
	
	public BigDecimal calculaTotalLiquido(PedidoItemDTO pedidoItemDTO) {
		Produto produto = carregaProduto(pedidoItemDTO.getIdproduto());
		BigDecimal totalProdutos = pedidoItemDTO.getQuantidade().multiply(produto.getPreco());
		BigDecimal totalAcrecimo = calculaTotalAcrescimo(pedidoItemDTO);
		BigDecimal totalDesconto = calculaTotalDesconto(pedidoItemDTO);
		BigDecimal totalLiquido = totalProdutos.add(totalAcrecimo).subtract(totalDesconto);
		
		return totalLiquido;
	}
	
	public boolean existeItemTipo(EnumProdutoServico tipo, PedidoDTO pedidoDTO) {
		for (PedidoItemDTO p : pedidoDTO.getPedidoitemDTO()) {
			Produto produto = carregaProduto(p.getIdproduto());
			if (tipo == produto.getTipo()) {
				return true;
			}
		}
		return false;
	}
}

