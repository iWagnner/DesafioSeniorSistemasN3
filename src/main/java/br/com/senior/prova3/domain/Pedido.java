package br.com.senior.prova3.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.senior.prova3.domain.enums.EnumSituacaoPedido;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */

@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private Long numero;
	@ManyToOne
	@JoinColumn(name = "idcliente" )
	private Pessoa cliente;
	@ManyToOne
	@JoinColumn(name = "idusuariogerador")
	private Usuario usuarioGerador;
	private EnumSituacaoPedido situacao = EnumSituacaoPedido.ABERTO;
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
	@JsonIgnore
	@OneToMany(mappedBy = "pedido")
	private List<PedidoItem> pedidoitem = new ArrayList<>();
	private Long currenttimemillis;

	public Pedido() {
		super();
	}

	public Pedido(UUID id, Pessoa cliente, Usuario usuarioGerador, Long numero, EnumSituacaoPedido situacao, BigDecimal totalProdutos, BigDecimal totalServicos, BigDecimal totalDesconto, BigDecimal totalAcrescimo,
			BigDecimal totalBruto, BigDecimal totalLiquido, BigDecimal percentualDesconto, String observacoes) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.usuarioGerador = usuarioGerador;
		this.numero = numero;
		this.situacao = situacao;
		this.totalProdutos = totalProdutos;
		this.totalServicos = totalServicos;
		this.totalDesconto = totalDesconto;
		this.totalAcrescimo = totalAcrescimo;
		this.totalBruto = totalBruto;
		this.totalLiquido = totalLiquido;
		this.percentualDesconto = percentualDesconto;
		this.observacoes = observacoes;
		this.currenttimemillis = System.currentTimeMillis();
	}

	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuarioGerador() {
		return usuarioGerador;
	}

	public void setUsuarioGerador(Usuario usuarioGerador) {
		this.usuarioGerador = usuarioGerador;
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

	public List<PedidoItem> getPedidoitem() {
		return pedidoitem;
	}

	public void setPedidoitem(List<PedidoItem> pedidoitem) {
		this.pedidoitem = pedidoitem;
	}

	public Long getCurrenttimemillis() {
		return currenttimemillis;
	}

	public void setCurrenttimemillis(Long currenttimemillis) {
		this.currenttimemillis = currenttimemillis;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}

}
