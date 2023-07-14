package br.com.senior.prova3.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.senior.prova3.domain.enums.EnumAtivoInativo;
import br.com.senior.prova3.domain.enums.EnumProdutoServico;
import br.com.senior.prova3.domain.enums.EnumSimNao;

/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private Long codigo;
	@ManyToOne
	@JoinColumn(name = "idfornecedor")
	private Pessoa fornecedor;
	private String nome;
	private EnumProdutoServico tipo;
	private EnumAtivoInativo situacao;
	private BigDecimal preco = BigDecimal.ZERO;
	private String siglaUnidadeMedida;
	private String observacao;
	private EnumSimNao permiteExcluir;
	private BigDecimal estoqueDisponivel = BigDecimal.ZERO;
	private BigDecimal estoqueBloqueado = BigDecimal.ZERO;
	private BigDecimal estoqueTotal = BigDecimal.ZERO;
	private EnumSimNao permiteEstoqueNegativo;
	@JsonIgnore
	@OneToMany(mappedBy = "produto")
	private List<PedidoItem> pedidoitem = new ArrayList<>();
	private Long currenttimemillis;

	public Produto() {
		super();
	}

	public Produto(UUID id, Long codigo, Pessoa fornecedor, String nome, EnumProdutoServico tipo,
			EnumAtivoInativo situacao, BigDecimal preco, String siglaUnidadeMedida, String observacao,
			EnumSimNao permiteExcluir, BigDecimal estoqueDisponivel, BigDecimal estoqueBloqueado,
			BigDecimal estoqueTotal, EnumSimNao permiteEstoqueNegativo, List<PedidoItem> pedidoitem) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.fornecedor = fornecedor;
		this.nome = nome;
		this.tipo = tipo;
		this.situacao = situacao;
		this.preco = preco;
		this.siglaUnidadeMedida = siglaUnidadeMedida;
		this.observacao = observacao;
		this.permiteExcluir = permiteExcluir;
		this.estoqueDisponivel = estoqueDisponivel;
		this.estoqueBloqueado = estoqueBloqueado;
		this.estoqueTotal = estoqueTotal;
		this.permiteEstoqueNegativo = permiteEstoqueNegativo;
		this.pedidoitem = pedidoitem;
		this.currenttimemillis = System.currentTimeMillis();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Pessoa getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Pessoa fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EnumProdutoServico getTipo() {
		return tipo;
	}

	public void setTipo(EnumProdutoServico tipo) {
		this.tipo = tipo;
	}

	public EnumAtivoInativo getSituacao() {
		return situacao;
	}

	public void setSituacao(EnumAtivoInativo situacao) {
		this.situacao = situacao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getSiglaUnidadeMedida() {
		return siglaUnidadeMedida;
	}

	public void setSiglaUnidadeMedida(String siglaUnidadeMedida) {
		this.siglaUnidadeMedida = siglaUnidadeMedida;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public EnumSimNao getPermiteExcluir() {
		return permiteExcluir;
	}

	public void setPermiteExcluir(EnumSimNao permiteExcluir) {
		this.permiteExcluir = permiteExcluir;
	}

	public BigDecimal getEstoqueDisponivel() {
		return estoqueDisponivel;
	}

	public void setEstoqueDisponivel(BigDecimal estoqueDisponivel) {
		this.estoqueDisponivel = estoqueDisponivel;
	}

	public BigDecimal getEstoqueBloqueado() {
		return estoqueBloqueado;
	}

	public void setEstoqueBloqueado(BigDecimal estoqueBloqueado) {
		this.estoqueBloqueado = estoqueBloqueado;
	}

	public BigDecimal getEstoqueTotal() {
		return estoqueTotal;
	}

	public void setEstoqueTotal(BigDecimal estoqueTotal) {
		this.estoqueTotal = estoqueTotal;
	}

	public EnumSimNao getPermiteEstoqueNegativo() {
		return permiteEstoqueNegativo;
	}

	public void setPermiteEstoqueNegativo(EnumSimNao permiteEstoqueNegativo) {
		this.permiteEstoqueNegativo = permiteEstoqueNegativo;
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}

}
