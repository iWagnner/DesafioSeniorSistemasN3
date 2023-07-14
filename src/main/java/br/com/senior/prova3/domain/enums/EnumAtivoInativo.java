package br.com.senior.prova3.domain.enums;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public enum EnumAtivoInativo {
	ATIVO("A", "Ativo"), //
	INATIVO("I", "Inativo");

	private String id;
	private String descricao;

	private EnumAtivoInativo(String id, String descricao) {
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

	public boolean isAtivo() {
		return this == ATIVO;
	}

	public boolean isInativo() {
		return this == INATIVO;
	}
	
	public static EnumAtivoInativo toEnum(String id) {
		if (id == null) {
			return null;
		}

		for (EnumAtivoInativo x : EnumAtivoInativo.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Situação Inválida");
	}

}
