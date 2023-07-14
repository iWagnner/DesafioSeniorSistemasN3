package br.com.senior.prova3.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.senior.prova3.domain.PedidoItem;
import br.com.senior.prova3.domain.Produto;
import br.com.senior.prova3.domain.enums.EnumAtivoInativo;
import br.com.senior.prova3.domain.enums.EnumProdutoServico;
import br.com.senior.prova3.domain.enums.EnumSimNao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private UUID id;
	private Long codigo;
	@NotNull(message = "O campo IDFORNECEDOR é requerido")
	private UUID idfornecedor;
	@NotBlank(message = "O campo NOME é requerido")
	private String nome;
	@NotNull(message = "O campo TIPO é requerido")
	private EnumProdutoServico tipo;
	@NotNull(message = "O campo SITUACAO é requerido")
	private EnumAtivoInativo situacao;
	@NotNull(message = "O campo PRECO é requerido")
	private BigDecimal preco = BigDecimal.ZERO;
	private String siglaUnidadeMedida;
	private String observacao;
	@NotNull(message = "O campo PODEEXCLUIR é requerido")
	private EnumSimNao permiteExcluir;
	@NotNull(message = "O campo ESTOQUEDISPONIVEL é requerido")
	private BigDecimal estoqueDisponivel = BigDecimal.ZERO;
	@NotNull(message = "O campo ESTOQUEBLOQUEADO é requerido")
	private BigDecimal estoqueBloqueado = BigDecimal.ZERO;
	private BigDecimal estoqueTotal = BigDecimal.ZERO;
	@NotNull(message = "O campo PERMITEESTOQUENEGATIVO é requerido")
	private EnumSimNao permiteEstoqueNegativo;
	private Long currenttimemillis;
	private List<PedidoItem> pedidoitem = new ArrayList<>();

	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(Produto produto) {
		super();
		this.id = produto.getId();
		this.codigo = produto.getCodigo();
		this.idfornecedor = produto.getFornecedor().getId();
		this.nome = produto.getNome();
		this.tipo = produto.getTipo();
		this.situacao = produto.getSituacao();
		this.preco = produto.getPreco();
		this.siglaUnidadeMedida = produto.getSiglaUnidadeMedida();
		this.observacao = produto.getObservacao();
		this.permiteExcluir = produto.getPermiteExcluir();
		this.estoqueDisponivel = produto.getEstoqueDisponivel();
		this.estoqueBloqueado = produto.getEstoqueBloqueado();
		this.estoqueTotal = produto.getEstoqueTotal();
		this.permiteEstoqueNegativo = produto.getPermiteEstoqueNegativo();
		this.currenttimemillis = produto.getCurrenttimemillis();
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

	public UUID getIdfornecedor() {
		return idfornecedor;
	}

	public void setIdfornecedor(UUID idfornecedor) {
		this.idfornecedor = idfornecedor;
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

	public Long getCurrenttimemillis() {
		return currenttimemillis;
	}

	public void setCurrenttimemillis(Long currenttimemillis) {
		this.currenttimemillis = currenttimemillis;
	}

	public List<PedidoItem> getPedidoitem() {
		return pedidoitem;
	}

	public void setPedidoitem(List<PedidoItem> pedidoitem) {
		this.pedidoitem = pedidoitem;
	}

}
