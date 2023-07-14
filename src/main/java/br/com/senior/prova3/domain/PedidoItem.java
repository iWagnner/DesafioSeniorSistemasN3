package br.com.senior.prova3.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.senior.prova3.domain.enums.EnumProdutoServico;
import br.com.senior.prova3.domain.enums.EnumSimNao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */

@Entity
public class PedidoItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@ManyToOne
	@JoinColumn(name = "idpedido")
	private Pedido pedido;
	@ManyToOne
	@JoinColumn(name = "idproduto")
	private Produto produto;
	private Long sequencial;
    @Column(name = "codigoproduto")
	private Long codigoProduto;
    @Column(name = "nomeproduto")
	private String nomeProduto;
	private BigDecimal quantidade = BigDecimal.ZERO;
	@Column(name = "totalbruto")
	private BigDecimal totalBruto = BigDecimal.ZERO;
	@Column(name = "totalliquido")
	private BigDecimal totalLiquido = BigDecimal.ZERO;
	private BigDecimal desconto = BigDecimal.ZERO;
	private BigDecimal acrecimo = BigDecimal.ZERO;
	@Column(name = "totaldesconto")
	private BigDecimal totalDesconto = BigDecimal.ZERO;
	@Column(name = "totalacrescimo")
	private BigDecimal totalAcrescimo = BigDecimal.ZERO;
	@Column(name = "DescontoRateado")
	private BigDecimal descontoRateado = BigDecimal.ZERO;
	@Column(name = "siglaunidademedida")
	private String siglaUnidadeMedida;
	private EnumProdutoServico tipo;
	private EnumSimNao cancelado = EnumSimNao.NAO;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "datainclusao")
	private LocalDate dataInclusao = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "datacancelamento")
	private LocalDate dataCancelamento;
	private Long currenttimemillis;

	public PedidoItem() {
		super();
	}

	public PedidoItem(UUID id, Pedido pedido, Produto produto, Long sequencial, Long codigoProduto, String nomeProduto, BigDecimal quantidade, BigDecimal totalBruto, BigDecimal totalLiquido, BigDecimal desconto,
				 	  BigDecimal acrecimo, BigDecimal totalDesconto, BigDecimal totalAcrescimo, BigDecimal DescontoRateado, String siglaUnidadeMedida, EnumSimNao cancelado, EnumProdutoServico tipo) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.produto = produto;
		this.sequencial = sequencial;
		this.codigoProduto = codigoProduto;
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
		this.totalBruto = totalBruto;
		this.totalLiquido = totalLiquido;
		this.desconto = desconto;
		this.acrecimo = acrecimo;
		this.totalDesconto = totalDesconto;
		this.totalAcrescimo = totalAcrescimo;
		this.descontoRateado = DescontoRateado;
		this.siglaUnidadeMedida = siglaUnidadeMedida;
		this.cancelado = cancelado;
		this.tipo = tipo;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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

	public EnumSimNao getCancelado() {
		return cancelado;
	}

	public void setCancelado(EnumSimNao cancelado) {
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

	public void setCurrenttimemillis(Long currenttimemillis) {
		this.currenttimemillis = currenttimemillis;
	}
	
	public EnumProdutoServico getTipo() {
		return tipo;
	}

	public void setTipo(EnumProdutoServico tipo) {
		this.tipo = tipo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoItem other = (PedidoItem) obj;
		return Objects.equals(id, other.id);
	}

}
