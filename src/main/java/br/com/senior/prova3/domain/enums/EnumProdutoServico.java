package br.com.senior.prova3.domain.enums;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public enum EnumProdutoServico {
	PRODUTO("P", "Produto"), //
	SERVICO("S", "Serviço");

	private String id;
	private String descricao;

	private EnumProdutoServico(String id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public String toString() {
		return new StringBuilder().append(id).append(" - ").append(descricao).toString();
	}

	public boolean isProduto() {
		return this == PRODUTO;
	}

	public boolean isServico() {
		return this == SERVICO;
	}
	
	public static EnumProdutoServico toEnum(String id) {
		if (id == null) {
			return null;
		}

		for (EnumProdutoServico x : EnumProdutoServico.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Tipo Inválido");
	}

}
