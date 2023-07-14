package br.com.senior.prova3.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import br.com.senior.prova3.domain.PedidoItem;
import br.com.senior.prova3.domain.enums.EnumProdutoServico;
import jakarta.validation.constraints.NotBlank;

public class PedidoItemDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private UUID id;
	@NotBlank(message = "O campo IDPEDIDO é requerido")
	private UUID idpedido;
	@NotBlank(message = "O campo PRODUTO é requerido")
	private UUID idproduto;
	private Long sequencial;
	private Long codigoProduto;
	private String nomeProduto;
	@NotBlank(message = "O campo QUANTIDADE é requerido")
	private BigDecimal quantidade = BigDecimal.ZERO;
	private BigDecimal totalBruto = BigDecimal.ZERO;
	private BigDecimal totalLiquido = BigDecimal.ZERO;
	private BigDecimal desconto = BigDecimal.ZERO;
	private BigDecimal acrecimo = BigDecimal.ZERO;
	private BigDecimal totalDesconto = BigDecimal.ZERO;
	private BigDecimal totalAcrescimo = BigDecimal.ZERO;
	private BigDecimal descontoRateado = BigDecimal.ZERO;
	private String siglaUnidadeMedida;
	private String cancelado;
	private LocalDate dataInclusao = LocalDate.now();
	private LocalDate dataCancelamento;
	private EnumProdutoServico tipo;
	private Long currenttimemillis;

	public PedidoItemDTO() {
		super();
	}

	public PedidoItemDTO(PedidoItem pedidoItem) {
		super();
		this.id = pedidoItem.getId();
		this.idpedido = pedidoItem.getPedido().getId();
		this.idproduto = pedidoItem.getProduto().getId();
		this.sequencial = pedidoItem.getSequencial();
		this.codigoProduto = pedidoItem.getCodigoProduto();
		this.nomeProduto = pedidoItem.getNomeProduto();
		this.quantidade = pedidoItem.getQuantidade();
		this.totalBruto = pedidoItem.getTotalBruto();
		this.totalLiquido = pedidoItem.getTotalLiquido();
		this.desconto = pedidoItem.getDesconto();
		this.acrecimo = pedidoItem.getAcrecimo();
		this.totalDesconto = pedidoItem.getTotalDesconto();
		this.totalAcrescimo = pedidoItem.getTotalAcrescimo();
		this.descontoRateado = pedidoItem.getDescontoRateado();
		this.siglaUnidadeMedida = pedidoItem.getSiglaUnidadeMedida();
		this.cancelado = pedidoItem.getCancelado().getId();
		this.dataInclusao = pedidoItem.getDataInclusao();
		this.dataCancelamento = pedidoItem.getDataCancelamento();
		this.tipo = pedidoItem.getTipo();
		this.currenttimemillis = pedidoItem.getCurrenttimemillis();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getIdpedido() {
		return idpedido;
	}

	public void setIdpedido(UUID idpedido) {
		this.idpedido = idpedido;
	}

	public UUID getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(UUID idproduto) {
		this.idproduto = idproduto;
	}

	public Long getSequencial() {
		return sequencial;
	}

	public void setSequencial(Long sequencial) {
		this.sequencial = sequencial;
	}

	public Long getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
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

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getAcrecimo() {
		return acrecimo;
	}

	public void setAcrecimo(BigDecimal acrecimo) {
		this.acrecimo = acrecimo;
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

	public BigDecimal getDescontoRateado() {
		return descontoRateado;
	}

	public void setDescontoRateado(BigDecimal DescontoRateado) {
		this.descontoRateado = DescontoRateado;
	}

	public String getSiglaUnidadeMedida() {
		return siglaUnidadeMedida;
	}

	public void setSiglaUnidadeMedida(String siglaUnidadeMedida) {
		this.siglaUnidadeMedida = siglaUnidadeMedida;
	}

	public String getCancelado() {
		return cancelado;
	}

	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}

	public LocalDate getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(LocalDate dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public LocalDate getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(LocalDate dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public Long getCurrenttimemillis() {
		return currenttimemillis;
	}
	
	public EnumProdutoServico getTipo() {
		return tipo;
	}

	public void setTipo(EnumProdutoServico tipo) {
		this.tipo = tipo;
	}

	public void setCurrenttimemillis(Long currenttimemillis) {
		this.currenttimemillis = currenttimemillis;
	}
}
