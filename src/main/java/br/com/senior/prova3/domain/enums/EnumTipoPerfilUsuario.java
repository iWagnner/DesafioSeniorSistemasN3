package br.com.senior.prova3.domain.enums;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public enum EnumTipoPerfilUsuario {
	ADMINISTRADOR(1, "Administrador"), //
	GERENTE(2, "Gerente"), //
	SUPERVISOR(3, "Supervisor"),  //
	VENDEDOR(4, "Vendedor"),//
	OPERADORPDV(5, "Operador PDV"), //
	FINANCEIRO(6, "Financeiro"), //
	ANALISTACREDITO(7, "Analista de crédito");

	private Integer id;
	private String descricao;

	private EnumTipoPerfilUsuario(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String toString() {
		return new StringBuilder().append(id).append(" - ").append(descricao).toString();
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EnumTipoPerfilUsuario toEnum(Integer id) {
		if (id == null) {
			return null;
		}

		for (EnumTipoPerfilUsuario x : EnumTipoPerfilUsuario.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Tipo Perfil Usuario Inválida");
	}
}
