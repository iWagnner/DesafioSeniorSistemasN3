package br.com.senior.prova3.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.senior.prova3.domain.Pedido;
import br.com.senior.prova3.domain.PedidoItem;
import br.com.senior.prova3.domain.enums.EnumSituacaoPedido;
import br.com.senior.prova3.services.PedidoService;
import jakarta.validation.constraints.NotNull;

public class PedidoDTO implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(PedidoService.class);

	private static final long serialVersionUID = 1L;

	private UUID id;
	@NotNull(message = "O campo IDCLIENTE é requerido")
	private UUID idCliente;
	@NotNull(message = "O campo ISUSUARIOGERADOR é requerido")
	private UUID idUsuarioGerador;
	private Long numero;
	@NotNull(message = "O campo SITUCAO é requerido")
	private EnumSituacaoPedido situacao;
	private BigDecimal totalProdutos = BigDecimal.ZERO;
	private BigDecimal totalServicos = BigDecimal.ZERO;
	private BigDecimal totalDesconto = BigDecimal.ZERO;
	private BigDecimal totalAcrescimo = BigDecimal.ZERO;
	private BigDecimal totalBruto = BigDecimal.ZERO;
	private BigDecimal totalLiquido = BigDecimal.ZERO;
	private BigDecimal percentualDesconto = BigDecimal.ZERO;
	private String observacoes;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCriacao = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFinalizacao;
	private List<PedidoItemDTO> pedidoitem = new ArrayList<>();
	private Long currenttimemillis;

	public PedidoDTO() {
		super();
	}

	public PedidoDTO(Pedido pedido) {
		super();
		this.id = pedido.getId();
		this.idCliente = pedido.getCliente().getId();
		this.idUsuarioGerador = pedido.getUsuarioGerador().getId();
		this.numero = pedido.getNumero();
		this.situacao = pedido.getSituacao();
		this.totalProdutos = pedido.getTotalProdutos();
		this.totalServicos = pedido.getTotalServicos();
		this.totalDesconto = pedido.getTotalDesconto();
		this.totalAcrescimo = pedido.getTotalAcrescimo();
		this.totalBruto = pedido.getTotalBruto();
		this.totalLiquido = pedido.getTotalLiquido();
		this.percentualDesconto = pedido.getPercentualDesconto();
		this.observacoes = pedido.getObservacoes();
		this.dataCriacao = pedido.getDataCriacao();
		this.dataFinalizacao = pedido.getDataFinalizacao();
		this.pedidoitem = carregaListPedidoItem(pedido.getPedidoitem());
		for(PedidoItemDTO p : pedidoitem) {
			logger.info("333 = " + p.getTotalBruto());
		}
		this.currenttimemillis = pedido.getCurrenttimemillis();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(UUID idCliente) {
		this.idCliente = idCliente;
	}

	public UUID getIdUsuarioGerador() {
		return idUsuarioGerador;
	}

	public void setIdUsuarioGerador(UUID idUsuarioGerador) {
		this.idUsuarioGerador = idUsuarioGerador;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public EnumSituacaoPedido getSituacao() {
		return situacao;
	}

	public void setSituacao(EnumSituacaoPedido situacao) {
		this.situacao = situacao;
	}

	public BigDecimal getTotalProdutos() {
		return totalProdutos;
	}

	public void setTotalProdutos(BigDecimal totalProdutos) {
		this.totalProdutos = totalProdutos;
	}

	public BigDecimal getTotalServicos() {
		return totalServicos;
	}

	public void setTotalServicos(BigDecimal totalServicos) {
		this.totalServicos = totalServicos;
	}

	public BigDecimal getTotalDesconto() {
		return totalDesconto;
	}

	public void setTotalDesconto(BigDecimal totalDesconto) {
		this.totalDesconto = totalDesconto;
	}

	public BigDecimal getTotalAcrescimo() {
		return totalAcrescimo;
	}

	public void setTotalAcrescimo(BigDecimal totalAcrescimo) {
		this.totalAcrescimo = totalAcrescimo;
	}

	public BigDecimal getTotalBruto() {
		return totalBruto;
	}

	public void setTotalBruto(BigDecimal totalBruto) {
		this.totalBruto = totalBruto;
	}

	public BigDecimal getTotalLiquido() {
		return totalLiquido;
	}

	public void setTotalLiquido(BigDecimal totalLiquido) {
		this.totalLiquido = totalLiquido;
	}

	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(BigDecimal percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDate getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(LocalDate dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public Long getCurrenttimemillis() {
		return currenttimemillis;
	}

	public void setCurrenttimemillis(Long currenttimemillis) {
		this.currenttimemillis = currenttimemillis;
	}
	
	public List<PedidoItemDTO> carregaListPedidoItem(List<PedidoItem> pedidoItem){
		List<PedidoItemDTO> listPedidoItemDTO = new ArrayList<>();
		for(PedidoItem p: pedidoItem) {
			PedidoItemDTO pedidoItemDTO = new PedidoItemDTO(p);
			listPedidoItemDTO.add(pedidoItemDTO);
		}
		return listPedidoItemDTO;
	}

	public List<PedidoItemDTO> getPedidoitemDTO() {
		return pedidoitem;
	}

	public void setPedidoitemDTO(List<PedidoItemDTO> pedidoitemDTO) {
		this.pedidoitem = pedidoitemDTO;
	}
	
	

}
